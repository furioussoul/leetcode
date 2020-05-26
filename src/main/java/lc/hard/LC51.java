package main.java.lc.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n皇后
 */
public class LC51 {

    int[] dx = {-1,1,0,0,-1,-1,1,1};
    int[] dy = {0,0,-1,1,-1,1,-1,1};

    public List<List<String>> solveNQueens(int n) {
        //初始化棋盘
        int[][] tableMark = new int[n][n];
        List<String> queueLoc = new ArrayList<>(n);
        char[] line = new char[n];
        Arrays.fill(line, '.');
        for(int i = 0; i < n; i++){
            tableMark[i] = new int[n];
            Arrays.fill(tableMark[i], 0);
            queueLoc.add(String.copyValueOf(line));
        }

        List<List<String>> rsl = new ArrayList<>(100);
        gen(0, n, tableMark, queueLoc, rsl);
        return rsl;
    }
    /**
     * k 已完成放置的皇后数量
     * n 皇后总数=棋盘大小
     */
    void gen(int k, int n, int[][] tableMark, List<String> queueLoc, List<List<String>> lst){
        if(k==n){
            List<String> cp = new ArrayList<>(queueLoc);
            lst.add(cp);
            return;
        }

        //n列
        for(int i = 0; i < n;i++){

            if(tableMark[k][i] == 0){
                //可放置

                //保存当前状态用来回溯
                int[][] tabelTmp = cloneMatrix(tableMark);
                String locLineTmp = queueLoc.get(k);

                putDown(k, i, tableMark, queueLoc);
                // printMatrix(tableMark);
                // printLoc(queueLoc);

                gen(k+1, n, tableMark, queueLoc, lst);

                //回溯
                tableMark = tabelTmp;
                queueLoc.set(k, locLineTmp);

            }
        }
    }

    void putDown(int x, int y, int[][] tableMark, List<String> queueLoc){

        tableMark[x][y] = 1;
        String line = queueLoc.get(x);
        queueLoc.set(x, line.substring(0,y) + 'Q' + line.substring(y+1, line.length()));

        for(int i = 1; i < queueLoc.size();i++){
            for(int j = 0; j < 8; j++){
                //8个攻击方向
                int newx = x + i*dx[j];
                int newy = y + i*dy[j];

                if(newx <0 || newy <0 || newx>=tableMark.length || newy >= tableMark.length){
                    continue;
                }

                tableMark[newx][newy] = 1;
            }
        }
    }

    public int[][] cloneMatrix(int[][] matrix){
        int[][] mt = new int[matrix.length][matrix.length];
        for(int i = 0; i< matrix.length; i++){
            System.arraycopy(matrix[i], 0, mt[i], 0, matrix[i].length);
        }
        return mt;
    }

    public void printMatrix(int[][] table){
        for(int i = 0; i < table.length;i++){
            for(int j = 0; j < table.length;j++){
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void printLoc(List<String> loc){
        for(String l : loc){
            System.out.println(l);
        }
    }
}
