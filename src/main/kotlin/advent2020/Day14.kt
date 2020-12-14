package advent2020

import java.util.LinkedList

class Day14(rawInput : List<String>) {
    private val memoryUpdates = MemoryUpdate.parse(rawInput)

    fun solvePart1() : Long {
        val addressMap = mutableMapOf<Long, Long>()

        memoryUpdates.forEach { update ->
            addressMap[update.addr] = calculateValue(update)
        }

        return addressMap.values.sum()
    }

    fun solvePart2() : Long {
        val addressMap = mutableMapOf<Long, Long>()

        memoryUpdates.forEach { update ->
            calculateAddresses(update).forEach { address ->
                addressMap[address] = update.value
            }
        }

        return addressMap.values.sum()
    }

    private fun calculateAddresses(update: MemoryUpdate): List<Long> =
        update.addr
            .toString(2)
            .padStart(update.mask.length, '0')
            .mapIndexed {index, char ->
                if (update.mask[index] != '0')
                    update.mask[index]
                else
                    char
            }
            .joinToString("")
            .let { addr ->
                val unresolvedAddresses = LinkedList(listOf(addr))
                val resolvedAddresses = mutableListOf<String>()

                while (unresolvedAddresses.isNotEmpty()) {
                    val value = unresolvedAddresses.poll()
                    val wildCardIndex = value.indexOfFirst { it == 'X' }
                    if (wildCardIndex == -1)
                        resolvedAddresses.add(value)
                    else {
                        for (charVal in listOf('0', '1')) {
                           unresolvedAddresses.add(value.replaceCharAt(wildCardIndex, charVal))
                        }
                    }
                }

                resolvedAddresses.map { it.toLong(2) }
            }

    private fun String.replaceCharAt(index : Int, char : Char) : String =
        this.substring(0 until index) + char + this.substring(index + 1)


    private fun calculateValue(update: MemoryUpdate) : Long =
        update.value
            .toString(2)
            .padStart(update.mask.length, '0')
            .mapIndexed {index, char ->
                if (update.mask[index] != 'X')
                    update.mask[index]
                else
                    char
            }
            .joinToString("")
            .toLong(2)

     data class MemoryUpdate(val mask : String, val addr : Long, val value : Long) {
        companion object {
            fun parse(rawInput : List<String>) : List<MemoryUpdate> {
                var currentMask = rawInput.first()
                    .substring(7)

                return rawInput.drop(1)
                    .asSequence()
                    .mapNotNull { input ->
                        if (input.startsWith("mask")) {
                            currentMask = input.substring(7)
                            null
                        } else {
                            val (addr, value) = input.split(" ", "=", "[", "]")
                                .drop(1)
                                .filter { it.isNotEmpty() }
                                .map { it.toLong() }
                            MemoryUpdate(currentMask, addr, value)
                        }
                    }.toList()
            }
        }
    }
}