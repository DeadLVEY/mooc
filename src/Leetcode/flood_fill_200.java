package Leetcode;

/**
 * 200：岛屿问题
 * 思路：遍历岛这个二维数组，如果当前数为1，则进入感染函数并将岛个数+1
 * 感染函数：其实就是一个递归标注的过程，它会将所有相连的1都标注成2
 */
public class flood_fill_200 {
    public int numIslands(char[][] grid) {
        int isLandNum = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    infect(grid,i,j);
                    isLandNum++;
                }
            }
        }
        return isLandNum;
    }

    private void infect(char[][] grid, int i, int j) {
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1'){
            return;
        }
        grid[i][j]='2';
        infect(grid,i-1,j);
        infect(grid,i+1,j);
        infect(grid,i,j-1);
        infect(grid,i,j+1);
    }
}
