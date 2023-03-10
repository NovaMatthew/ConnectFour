import GameConstants.BLUE_WON
import GameConstants.RED_WON
import GameConstants.TIE
import java.text.BreakIterator

/**
 * TicTacToe class implements the interface
 * @author Matthew Nova
 * @assignment Connect Four
 * @due 2/2/2022
 */
class FourInARow
/**
 * clear board and set current player
 */
    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS) { 0 } }

    // Clears game board
    override fun clearBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                board[row][col] = GameConstants.EMPTY
            }
        }
    }

    // Sets player position (both computer and user)
    override fun setMove(player: Int, location: Int) {
        val rowPlay = location / 6
        val colPlay = location % 6

        // sets player location for user
        if (player == 1) {
            for (row in 0 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS) {
                    board[rowPlay][colPlay] = GameConstants.RED
                }
            }

            // sets player location to computer
        } else if (player == 2) {
            for (row in 0 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS) {
                    board[rowPlay][colPlay] = GameConstants.BLUE
                }
            }
        }
    }

    // randomizes computer location
    override val computerMove: Int
        get() {
            var inRange: Boolean = false
            var randEmptySlot: Int = 0
            do {
                randEmptySlot = (0..35).random()
                val rowPlay = randEmptySlot / 6
                val colPlay = randEmptySlot % 6
                if (board[rowPlay][colPlay] == GameConstants.EMPTY) {
                    inRange = true
                }
            } while (!inRange)
            return randEmptySlot
        }

    // check for winner
    override fun checkForWinner(): Int {
        var redCount = 0
        var blueCount = 0
        var total = 0

        //checks for horizontal win
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (col == 0) {
                    redCount = 0
                    blueCount = 0
                }
                if (board[row][col] == GameConstants.RED) {
                    redCount++
                    blueCount = 0
                    if (redCount >= 4) {
                        return RED_WON
                    }
                } else if (board[row][col] == GameConstants.BLUE) {
                    blueCount++
                    redCount = 0
                    if (blueCount >= 4) {
                        return BLUE_WON
                    }
                } else {
                    redCount = 0
                    blueCount = 0
                }
            }
        }

        redCount = 0
        blueCount = 0

        // check for vertical win
        for (col in 0 until GameConstants.COLS) {
            for (row in 0 until GameConstants.ROWS) {
                if (board[row][col] == GameConstants.RED) {
                    redCount++
                    blueCount = 0
                    if (redCount >= 4) {
                        return RED_WON
                    }
                } else if (board[row][col] == GameConstants.BLUE) {
                    blueCount++
                    redCount = 0
                    if (blueCount >= 4) {
                        return BLUE_WON
                    }
                } else {
                    redCount = 0
                    blueCount = 0
                }
            }
        }


        /** Citation
         * - Shaun Halverson
         * https://www.youtube.com/watch?v=i_4ZWjmybWs
         * Referenced Shaun's Diagonal check algorithm
         */

        // checks Diagonal win left ---> right
        for (row in 0 until GameConstants.ROWS - 3) {
            for (col in 0 until GameConstants.COLS - 3) {
                if (board[row][col] == GameConstants.RED && board[row + 1][col + 1] == GameConstants.RED && board[row + 2][col + 2] == GameConstants.RED && board[row + 3][col + 3] == GameConstants.RED) {
                    return RED_WON
                } else if (board[row][col] == GameConstants.BLUE && board[row + 1][col + 1] == GameConstants.BLUE && board[row + 2][col + 2] == GameConstants.BLUE && board[row + 3][col + 3] == GameConstants.BLUE) {
                    return BLUE_WON
                }
            }
        }

        // checks Diagonal win right ---> left
        for (row in 0 until GameConstants.ROWS - 3) {
            for (col in 3 until GameConstants.COLS) {
                if (board[row][col] == GameConstants.RED && board[row + 1][col - 1] == GameConstants.RED && board[row + 2][col - 2] == GameConstants.RED && board[row + 3][col - 3] == GameConstants.RED) {
                    return RED_WON
                } else if (board[row][col] == GameConstants.BLUE && board[row + 1][col - 1] == GameConstants.BLUE && board[row + 2][col - 2] == GameConstants.BLUE && board[row + 3][col - 3] == GameConstants.BLUE) {
                    return BLUE_WON
                }
            }
        }


        // checks tie
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == GameConstants.RED || board[row][col] == GameConstants.BLUE) {
                    total++
                    if (total == 36) {
                        println(total)
                        return TIE
                    }
                }
            }
        }

        return 0
    }


    /**
     * Print the game board
     */
    fun printBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println()
            if (row != GameConstants.ROWS - 1) {
                println("------------------------") // print horizontal partition
            }
        }
        println()
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}
