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

    private fun calculateValue(update: MemoryUpdate) : Long =
        applyMask(update.mask, update.value) { maskValue -> maskValue != 'X' }
            .toLong(2)

    private fun calculateAddresses(update: MemoryUpdate): List<Long> =
        applyMask(update.mask, update.addr) { maskValue -> maskValue != '0' }
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

    private fun applyMask(mask : String, value : Long, useMaskValue : (maskValue : Char) -> Boolean) : String =
        value
            .toString(2)
            .padStart(mask.length, '0')
            .mapIndexed {index, char ->
                if (useMaskValue(mask[index])) mask[index] else char
            }
            .joinToString("")

     data class MemoryUpdate(val mask : String, val addr : Long, val value : Long) {
        companion object {
            private const val MASK_PREFIX = "mask = "

            fun parse(rawInput : List<String>) : List<MemoryUpdate> {
                var currentMask = rawInput.first()
                    .substring(MASK_PREFIX.length)

                return rawInput.drop(1)
                    .mapNotNull { input ->
                        if (input.startsWith("mask")) {
                            currentMask = input.substring(MASK_PREFIX.length)
                            null
                        } else {
                            val (addr, value) = input.split(" ", "=", "[", "]")
                                .drop(1)
                                .filter { it.isNotEmpty() }
                                .map { it.toLong() }
                            MemoryUpdate(currentMask, addr, value)
                        }
                    }
            }
        }
    }
}