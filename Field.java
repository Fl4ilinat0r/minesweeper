import com.sun.corba.se.spi.logging.CORBALogDomains;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 14/01/2015.
 */
public class Field {

    public class Cord {
        private int x;
        private int y;
        private boolean usable;
        private Random rand = new Random();


        public Cord() {
            this.x = 0;
            this.y = 0;
            this.usable = false;
        }

        public Cord(int x, int y) {
            this.x = x;
            this.y = y;
            IsUsable();
        }

        public void GenerateRandom(int maxX, int maxY) {
            this.x = rand.nextInt(maxX);
            this.y = rand.nextInt(maxY);
            IsUsable();
        }

        private void IsUsable() {
            if ((this.x + this.y) > -1) {
                this.usable = true;
            } else {
                this.usable = false;
            }
        }

        private Boolean IsUsableReturn() {
            IsUsable();
            return usable;

        }

        /*
        private void CalcNeighbours() {

            Cord tmp = new Cord();
            // calculate neighbours.
            //row above
            for (int i = -1; i < 1; i++) {
                tmp.x = this.x + i;
                tmp.y = this.y + 1;

                Neighbours.add(Neighbours.size(), tmp);
                System.out.println("add neig");
            }
            // row under
            for (int i = -1; i < 1; i++) {
                tmp.x = this.x + i;
                tmp.y = this.y - 1;
                Neighbours.add(Neighbours.size(), tmp);
                System.out.println("add neig");
            }
            // same row
            for (int i = -1; i < 0; i++) {
                tmp.y = this.y;
                if (i == -1) {
                    tmp.x = this.x + i;
                } else {
                    tmp.x = this.x + 1;
                }
                Neighbours.add(Neighbours.size(), tmp);
                System.out.println("add neig");
            }
        }
*/

    }


    //field class
    private int row;
    private int col;
    private int totalMines;
    private int[][] field;


    public Field(int col, int row, int totalMines) {
        this.row = row;
        this.col = col;
        this.totalMines = totalMines;

        field = new int[col][row];
        Fill();
    }

    public void Fill() {

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                //System.out.println(y);
                field[x][y] = 0;
            }
        }

        addMines();
    }

    public void addMine() {
        field[1][1] = 9;
    }

    public void addMines() {
        Random rand = new Random();
        Cord cord = new Cord();
        for (int i = 0; i < totalMines; i++) {
            cord.GenerateRandom(this.col, this.row);
            if (field[cord.x][cord.y] == 9) {
                // System.out.println("double mine!!!!!!!!!!!");
                i--;
            } else {
                ChangeOne(cord, 9);
            }
        }
    }

    public void ChangeOne(Cord cord, int value) {
        field[cord.x][cord.y] = value;
    }

    public void Show() {
        String output = "";
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                if (field[x][y] == 9) {
                    //   System.out.println("\u001B[31mX\u001B[0m");
                    //  String.format(output, ); + " ");
                    output += String.format("%3s", "X");
                } else {
                    output += String.format("%3s", field[x][y]);
                }
            }
            output += "\n";
        }
        System.out.println(output);
    }

    public void CalcNumbers() {
        Cord tmp = new Cord();
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                tmp.x = x;
                tmp.y = y;
                if (field[x][y] == 9) {
                } else {
                    field[x][y] = CalcNeighbours(tmp);
                }
            }
        }
    }

    private int CalcNeighbours(Cord cord) {
        // cord is cord where number get placed.

        int Mines = 0;
        Cord tmp = new Cord();
        // calculate neighbours.
        //row above
        for (int i = -1; i < 2; i++) {
            tmp.x = cord.x + i;
            tmp.y = cord.y + 1;

            // row above
            if (tmp.IsUsableReturn() == true) {
                try {
                    if (field[tmp.x][tmp.y] == 9) {
                        Mines += 1;
                        // System.out.println("found mine");
                    }
                } catch (Exception e) {
                }
            }
        }


        // row under
        // given cord 0,0


        for (int i = -1; i < 2; i++) {
            tmp.x = cord.x + i;
            tmp.y = cord.y - 1;
            if (tmp.IsUsableReturn() == true) {
                try {
                    if (field[tmp.x][tmp.y] == 9) {
                        Mines += 1;
                        //  System.out.println("found mine");
                    }
                } catch (Exception e) {
                }
            }
        }
        // same row
        for (int i = -1; i < 2; i++) {
            tmp.x = cord.x + i;
            tmp.y = cord.y;
            if (tmp.y == cord.y && tmp.x == cord.x) {

            } else {
                if (tmp.IsUsableReturn() == true) {

                    try {
                        if (field[tmp.x][tmp.y] == 9) {
                            Mines += 1;
                            //  System.out.println("found mine");
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return Mines;
    }

    public void click(int x, int y) {
        if (field[x][y] == 9) {
            System.out.println("you clicked on a mine you derp");
        }


    }

    public int GetCordValue(Cord cord) {
        return field[cord.x][cord.y];
    }

}


