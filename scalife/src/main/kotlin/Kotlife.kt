
open class Cell (val alive: Boolean = false) {
    fun nextGeneration(neighbours: Int) =
        if (neighbours == 3 || (alive && neighbours == 2)) AliveCell else DeadCell
}

object AliveCell : Cell(true)
object DeadCell : Cell()

class World (val columns: Int, val cells: List<Cell>) {
    companion object {
        val RANGE = 1
    }

    val rows = cells.size / columns

    fun neighbours (index: Int): List<Cell> {
        val indexRow = index / columns
        val indexColumn = index % columns
        val rowRange = indexRow - RANGE .. indexRow + RANGE
        val columnRange = indexColumn - RANGE .. indexColumn + RANGE

        fun isSameCell (c: Int, r: Int) = c == indexColumn && r == indexRow
        fun isInside (c: Int, r: Int) = c >= 0 && c < columns && r >= 0 && r < rows
        fun isNeighbour (c: Int, r: Int) = !isSameCell (c, r) && isInside(c,  r)
        fun isAlive (c: Int, r: Int) = cells[columns * r + c].alive

        return columnRange.map { c ->
            rowRange.map { r ->
                if (isNeighbour(c, r) && isAlive(c, r)) AliveCell else DeadCell
            }
        }.flatten()
    }

    fun nextGeneration(): World {
        val cs = (0..cells.size - 1).map { cells[it].nextGeneration (neighbours (it).size) }
        return World (columns, cs)
    }
}

