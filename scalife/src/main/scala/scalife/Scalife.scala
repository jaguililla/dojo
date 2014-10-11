package scalife

class Cell protected (val alive: Boolean = false) {
  def nextGeneration (neighbours: Int) =
    if (neighbours == 3 || (alive && neighbours == 2)) AliveCell else DeadCell
}

object AliveCell extends Cell (true)
object DeadCell extends Cell

class World (val columns: Int, val cells: List[Cell]) {
  val rows = cells.size / columns

  def neighbours (index: Int) = {
    import World._
    val indexRow = index / columns
    val indexColumn = index % columns
    val rowRange = indexRow - RANGE to indexRow + RANGE
    val columnRange = indexColumn - RANGE to indexColumn + RANGE

    def isSameCell (c: Int, r: Int) = c == indexColumn && r == indexRow
    def isInside (c: Int, r: Int) = c >= 0 && c < columns && r >= 0 && r < rows
    def isNeighbour (c: Int, r: Int) = !isSameCell (c, r) && isInside(c,  r)
    def isAlive (c: Int, r: Int) = cells (columns * r + c).alive

    for (c <- columnRange; r <- rowRange if isNeighbour (c, r) && isAlive (c, r)) yield AliveCell
  }

  def nextGeneration = {
    val cs = for (ii <- 0 until cells.size) yield cells (ii).nextGeneration (neighbours (ii).size)
    World (columns, cs.toList)
  }
}

object World {
  val RANGE = 1

  def apply (columns: Int, cells: List[Cell]) = new World (columns, cells)
}
