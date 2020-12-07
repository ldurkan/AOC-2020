package advent2020

class Day7(input : List<String>) {
    private val allBags = parse(input)

    fun solvePart1() : Int = bagsThatHoldRoot(allBags["shiny gold"] ?: error("Could not find shiny gold bag"))
        .count()

    fun solvePart2() : Int = bagsContainedInRoot(allBags["shiny gold"] ?: error("Could not find shiny gold bag")) - 1

    private fun bagsContainedInRoot(root : Bag) : Int {
        if (root.contains.isEmpty()) return 1
        return root.contains.map {
            val blah = it.second * bagsContainedInRoot(allBags[it.first]!!)
            blah
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
        private fun parse(input : List<String>) : Map<String, Bag> {
            val bags = mutableMapOf<String, Bag>()
            input.forEach { line ->
                val args = line.split(" contain ")
                var bag = Bag(args[0].substring(0 until args[0].lastIndexOf(' ')))
                bag = bags.putIfAbsent(bag.id, bag) ?: bag
                args[1].split(", ")
                    .map {it.substring(0 until it.lastIndexOf(' ')).trim()}
                    .forEach {
                        if (it != "no other") {
                            val firstSpace = it.indexOf(" ")
                            val name = it.substring(firstSpace + 1)
                            val amount = it.substring(0 until firstSpace).toInt()

                            bags.putIfAbsent(name, Bag(name))
                            bags[name]!!.containedIn.add(Pair(amount, bag.id))
                            bag.contains.add(Pair(name, amount))
                        }
                    }
            }

            return bags
        }
    }
}

data class Bag(val id: String) {
    val containedIn = mutableListOf<Pair<Int, String>>()

    val contains = mutableListOf<Pair<String, Int>>()
}