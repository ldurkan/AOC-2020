package advent2020

class Day7(input : List<String>) {
    private val allBags = parse(input)

    fun solvePart1() : Int = bagsThatHoldRoot(allBags[ROOT_BAG_NAME]
        ?: error("Could not find root bag $ROOT_BAG_NAME"))
        .count()

    fun solvePart2() : Int = bagsContainedInRoot(allBags[ROOT_BAG_NAME]
        ?: error("Could not find root bag $ROOT_BAG_NAME")) - 1

    private fun bagsContainedInRoot(root : Bag) : Int {
        if (root.contains.isEmpty()) return 1
        return root.contains.map {
            it.second * bagsContainedInRoot(allBags[it.first] ?: error("Couldn't find bag ${it.first}"))
        }.sum() + 1
    }

    private fun bagsThatHoldRoot(root : Bag, bagsConnectedToRoot : Set<String> = setOf()) : Set<String> {
        val bagsRootIsIn = root.containedIn.map { it.second }
        var currentBags = bagsConnectedToRoot + bagsRootIsIn

        bagsRootIsIn.forEach {
            currentBags = currentBags + bagsThatHoldRoot(allBags[it] ?: error("Could not find bag $it"), currentBags)
        }

        return currentBags
    }

    companion object {
        const val ROOT_BAG_NAME = "shiny gold"

        private fun parse(input : List<String>) : Map<String, Bag> {
            val bags = mutableMapOf<String, Bag>()
            input.forEach { line ->
                val args = line.split(" bags contain ")
                var topBag = Bag(args[0])
                topBag = bags.putIfAbsent(topBag.id, topBag) ?: topBag
                args[1].split(", ")
                    .map {it.substring(0 until it.lastIndexOf(' '))}
                    .forEach {
                        if (it != "no other") {
                            val firstSpace = it.indexOf(" ")
                            val name = it.substring(firstSpace + 1)
                            val amount = it.substring(0 until firstSpace).toInt()

                            bags.putIfAbsent(name, Bag(name))
                            bags[name]?.containedIn?.add(Pair(amount, topBag.id))
                            topBag.contains.add(Pair(name, amount))
                        }
                    }
            }

            return bags
        }
    }

    private data class Bag(val id: String) {
        val containedIn = mutableListOf<Pair<Int, String>>()

        val contains = mutableListOf<Pair<String, Int>>()
    }
}