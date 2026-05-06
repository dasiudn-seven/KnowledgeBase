//给你一个 m x n 的字符矩阵 boxGrid ，它表示一个箱子的侧视图。箱子的每一个格子可能为： 
//
// 
// '#' 表示石头 
// '*' 表示固定的障碍物 
// '.' 表示空位置 
// 
//
// 这个箱子被 顺时针旋转 90 度 ，由于重力原因，部分石头的位置会发生改变。每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。重力 不会 影
//响障碍物的位置，同时箱子旋转不会产生惯性 ，也就是说石头的水平位置不会发生改变。 
//
// 题目保证初始时 boxGrid 中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。 
//
// 请你返回一个 n x m 的矩阵，表示按照上述旋转后，箱子内的结果。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：box = [["#",".","#"]]
//输出：[["."],
//      ["#"],
//      ["#"]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：box = [["#",".","*","."],
//            ["#","#","*","."]]
//输出：[["#","."],
//      ["#","#"],
//      ["*","*"],
//      [".","."]]
// 
//
// 示例 3： 
//
// 
//
// 
//输入：box = [["#","#","*",".","*","."],
//            ["#","#","#","*",".","."],
//            ["#","#","#",".","#","."]]
//输出：[[".","#","#"],
//      [".","#","#"],
//      ["#","#","*"],
//      ["#","*","."],
//      ["#",".","*"],
//      ["#",".","."]]
// 
//
// 
//
// 提示： 
//
// 
// m == boxGrid.length 
// n == boxGrid[i].length 
// 1 <= m, n <= 500 
// boxGrid[i][j] 只可能是 '#' ，'*' 或者 '.' 。 
// 
//
// Related Topics 数组 双指针 矩阵 👍 48 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public char[][] rotateTheBox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] resbox = new char[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(resbox[i], '.');
        }

        for (int i = 0; i < m; i++) {
            int num = 0;
            for (int j = 0; j < n; j++) {
                if (boxGrid[i][j] == '*') {
                    resbox[j][m - 1 - i] = '*';
                    if (num > 0) {
                        int temp = j - 1;
                        for (; num > 0; num --) {
                            resbox[temp][m - 1 - i] = '#';
                            temp --;
                        }
                    }
                }

                if (boxGrid[i][j] == '#') {
                    num ++;
                }

                if (j == n - 1)
                {
                    if (num > 0) {
                        int temp = j;
                        for (; num > 0; num --) {
                            resbox[temp][m - 1 - i] = '#';
                            temp --;
                        }
                    }
                }
            }
        }
        return resbox;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
