import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesTest {

    ValidParentheses vp = new ValidParentheses();

    @Test
    public void testIsValid() {
        assertTrue(vp.isValid("()"));
        assertTrue(vp.isValid("()[]{}"));
        assertFalse(vp.isValid("(]"));
        assertFalse(vp.isValid("([)]"));
        }

    @Test
    public void testEdgeCases() {
        assertTrue(vp.isValid("")); // empty string
        assertFalse(vp.isValid("{[}")); // mismatch
        assertFalse(vp.isValid("{")); // single open bracket
        assertFalse(vp.isValid("}")); // single closed bracket
        }

    @Test
    public void testLongInput() {
        String longString = "({[([]{}[]()[]{}()[]()[]{}[](){}[])]})"; // long valid string
        assertTrue(vp.isValid(longString));
        }
}
