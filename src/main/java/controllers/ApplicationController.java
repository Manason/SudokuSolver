/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import models.*;
import ninja.Context;
import ninja.Result;
import ninja.Results;

import com.google.inject.Singleton;

@Singleton
public class ApplicationController {

    public Result index() {
        return Results.html().template("views/Sudoku/Sudoku.flt.html");
    }

    public Result solveBoard(Context context, int[][] testBoard){
        //can replace testBoard below with testBoard1 to test
        int[][] testBoard1 = {{3, 0, 0, 4, 0, 2, 1, 0, 8},
                {0, 0, 0, 0, 0, 5, 4, 7, 0},
                {0, 0, 5 ,0 ,0 ,0 ,0 ,0 ,0},
                {0, 0, 9, 2, 5, 0, 0, 0, 7},
                {0, 0, 0, 9, 0, 4, 0, 0, 0},
                {7, 0, 0, 0, 1, 3, 9, 0, 0},
                {0, 0, 0, 0, 0, 0, 7, 0, 0},
                {0, 4, 1, 5, 0, 0, 0, 0, 0},
                {8, 0, 7, 6, 0, 1, 0, 0, 5}};
        SudokuSolver ss = new SudokuSolver(testBoard);
        System.out.println("board to solve:");
        ss.printBoard(ss.board);
        ss.board = ss.solveBoard(ss.board);
        System.out.println("solved board:");
        ss.printBoard(ss.board);
        return Results.json().render(ss);
    }

}
