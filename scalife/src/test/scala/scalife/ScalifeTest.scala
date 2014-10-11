package scalife

import org.scalatest.FunSuite

class CellTest extends FunSuite {

  test ("An alive cell with less than 2 neighbours dies") {
    assert (!AliveCell.nextGeneration (1).alive)
  }

  test ("An alive cell with more than 3 neighbours dies") {
    assert (!AliveCell.nextGeneration (4).alive)
  }

  test ("An alive cell with 2 or 3 neighbours lives") {
    assert (AliveCell.nextGeneration (2).alive && AliveCell.nextGeneration (3).alive)
  }

  test ("A dead cell with 3 neighbours will born the in next generation") {
    assert (DeadCell.nextGeneration (3).alive)
  }
}

class WorldTest extends FunSuite {
  // Aliases to ease the definition of worlds
  val AA = AliveCell
  val __ = DeadCell

  test ("The world returns 0 neighbours for a cell with no alive cells around") {
    val w = World (3, List (
      __, __, __,
      __, __, __,
      __, __, __
    ))

    for (ii <- 0 until w.cells.size)
      assert (w.neighbours(ii).size == 0)
  }

  test ("The world returns the neighbours for all cells inside it") {
    val w = World (3, List (
      __, __, AA,
      __, AA, __,
      AA, __, AA
    ))

    assert (w.neighbours(0).size == 1)
    assert (w.neighbours(1).size == 2)
    assert (w.neighbours(2).size == 1)
    assert (w.neighbours(3).size == 2)
    assert (w.neighbours(4).size == 3)
    assert (w.neighbours(5).size == 3)
    assert (w.neighbours(6).size == 1)
    assert (w.neighbours(7).size == 3)
    assert (w.neighbours(8).size == 1)
  }

  test ("The world generates a new one following the game rules") {
    val worlds = Array (
      World (5, List (
        __, __, __, __, __,
        __, __, AA, __, __,
        __, AA, AA, AA, __,
        __, __, AA, __, __,
        __, __, __, __, __
      )),
      World (5, List (
        __, __, __, __, __,
        __, AA, AA, AA, __,
        __, AA, __, AA, __,
        __, AA, AA, AA, __,
        __, __, __, __, __
      )),
      World (5, List (
        __, __, AA, __, __,
        __, AA, __, AA, __,
        AA, __, __, __, AA,
        __, AA, __, AA, __,
        __, __, AA, __, __
      )),
      World (5, List (
        __, __, AA, __, __,
        __, AA, AA, AA, __,
        AA, AA, __, AA, AA,
        __, AA, AA, AA, __,
        __, __, AA, __, __
      )),
      World (5, List (
        __, AA, AA, AA, __,
        AA, __, __, __, AA,
        AA, __, __, __, AA,
        AA, __, __, __, AA,
        __, AA, AA, AA, __
      )),
      World (5, List (
        __, AA, AA, AA, __,
        AA, __, AA, __, AA,
        AA, AA, __, AA, AA,
        AA, __, AA, __, AA,
        __, AA, AA, AA, __
      ))
    )

    for (ii <- 1 until worlds.size)
      assert (worlds (ii - 1).nextGeneration.cells == worlds (ii).cells)
  }
}
