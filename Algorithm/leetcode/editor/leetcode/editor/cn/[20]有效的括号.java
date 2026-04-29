//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
// 
//
// 
//
// 示例 1： 
//
// 
// 输入：s = "()" 
// 
//
// 输出：true 
//
// 示例 2： 
//
// 
// 输入：s = "()[]{}" 
// 
//
// 输出：true 
//
// 示例 3： 
//
// 
// 输入：s = "(]" 
// 
//
// 输出：false 
//
// 示例 4： 
//
// 
// 输入：s = "([])" 
// 
//
// 输出：true 
//
// 示例 5： 
//
// 
// 输入：s = "([)]" 
// 
//
// 输出：false 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 4961 👎 0
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            switch (c)
            {
                case '(':
                    stack.push(1);
                    break;
                case '{':
                    stack.push(2);
                    break;
                case '[':
                    stack.push(3);
                    break;
                case ')':
                    if (stack.empty())
                    {
                        return false;
                    }
                    int res1 = stack.pop();
                    if (res1 != 1) {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.empty())
                    {
                        return false;
                    }
                    int res2 = stack.pop();
                    if (res2 != 2) {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.empty())
                    {
                        return false;
                    }
                    int res3 = stack.pop();
                    if (res3 != 3) {
                        return false;
                    }
                    break;
            };
        }

        return stack.empty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
