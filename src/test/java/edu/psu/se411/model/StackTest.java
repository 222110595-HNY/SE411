package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("Stack Class Tests")
public class StackTest {
    
    private Stack<String> stringStack;
    private Stack<Integer> integerStack;
    
    @BeforeEach
    void setUp() {
        stringStack = new Stack<>();
        integerStack = new Stack<>();
    }
    
    // ======================== CONSTRUCTOR TESTS ========================
    
    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        
        @Test
        @DisplayName("Default constructor creates stack with default capacity")
        void testDefaultConstructor() {
            Stack<String> stack = new Stack<>();
            assertNotNull(stack, "Stack should not be null");
            // Verify it's empty by attempting to pop
            assertThrows(NoSuchElementException.class, stack::pop);
        }
        
        @Test
        @DisplayName("Constructor with positive capacity creates usable stack")
        void testConstructorWithPositiveCapacity() {
            Stack<String> stack = new Stack<>(20);
            assertNotNull(stack, "Stack should not be null");
            assertThrows(NoSuchElementException.class, stack::pop);
        }
        
        @Test
        @DisplayName("Constructor with zero capacity defaults to 10")
        void testConstructorWithZeroCapacity() {
            Stack<String> stack = new Stack<>(0);
            assertNotNull(stack, "Stack should not be null");
            // Should still work normally
            stack.push("test");
            assertEquals("test", stack.pop());
        }
        
        @Test
        @DisplayName("Constructor with negative capacity defaults to 10")
        void testConstructorWithNegativeCapacity() {
            Stack<String> stack = new Stack<>(-5);
            assertNotNull(stack, "Stack should not be null");
            // Should still work normally
            stack.push("test");
            assertEquals("test", stack.pop());
        }
        
        @Test
        @DisplayName("Constructor with large capacity")
        void testConstructorWithLargeCapacity() {
            Stack<String> stack = new Stack<>(1000);
            assertNotNull(stack, "Stack should not be null");
            stack.push("test");
            assertEquals("test", stack.pop());
        }
    }
    
    // ======================== PUSH TESTS ========================
    
    @Nested
    @DisplayName("Push Method Tests")
    class PushTests {
        
        @Test
        @DisplayName("Push single element onto empty stack")
        void testPushSingleElement() {
            stringStack.push("hello");
            assertEquals("hello", stringStack.pop());
        }
        
        @Test
        @DisplayName("Push null element")
        void testPushNull() {
            stringStack.push(null);
            assertNull(stringStack.pop(), "Should be able to push and pop null");
        }
        
        @Test
        @DisplayName("Push multiple elements maintains order")
        void testPushMultipleElements() {
            stringStack.push("first");
            stringStack.push("second");
            stringStack.push("third");
            
            assertEquals("third", stringStack.pop());
            assertEquals("second", stringStack.pop());
            assertEquals("first", stringStack.pop());
        }
        
        @Test
        @DisplayName("Push empty string")
        void testPushEmptyString() {
            stringStack.push("");
            assertEquals("", stringStack.pop());
        }
        
        @Test
        @DisplayName("Push duplicate elements")
        void testPushDuplicateElements() {
            stringStack.push("duplicate");
            stringStack.push("duplicate");
            stringStack.push("duplicate");
            
            assertEquals("duplicate", stringStack.pop());
            assertEquals("duplicate", stringStack.pop());
            assertEquals("duplicate", stringStack.pop());
        }
        
        @Test
        @DisplayName("Push beyond initial capacity")
        void testPushBeyondCapacity() {
            Stack<String> stack = new Stack<>(2);
            stack.push("first");
            stack.push("second");
            stack.push("third");  // Exceeds capacity
            stack.push("fourth");  // Further exceeds
            
            assertEquals("fourth", stack.pop());
            assertEquals("third", stack.pop());
            assertEquals("second", stack.pop());
            assertEquals("first", stack.pop());
        }
        
        @Test
        @DisplayName("Push zero")
        void testPushZero() {
            integerStack.push(0);
            assertEquals(0, integerStack.pop());
        }
        
        @Test
        @DisplayName("Push negative number")
        void testPushNegativeNumber() {
            integerStack.push(-42);
            assertEquals(-42, integerStack.pop());
        }
        
        @Test
        @DisplayName("Push maximum integer value")
        void testPushMaxInteger() {
            integerStack.push(Integer.MAX_VALUE);
            assertEquals(Integer.MAX_VALUE, integerStack.pop());
        }
        
        @Test
        @DisplayName("Push minimum integer value")
        void testPushMinInteger() {
            integerStack.push(Integer.MIN_VALUE);
            assertEquals(Integer.MIN_VALUE, integerStack.pop());
        }
    }
    
    // ======================== POP TESTS ========================
    
    @Nested
    @DisplayName("Pop Method Tests")
    class PopTests {
        
        @Test
        @DisplayName("Pop from empty stack throws NoSuchElementException")
        void testPopFromEmptyStackThrowsException() {
            NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                stringStack::pop,
                "Pop from empty stack should throw NoSuchElementException"
            );
            assertEquals("Stack is empty, cannot pop", thrown.getMessage());
        }
        
        @Test
        @DisplayName("Pop single element from stack with one element")
        void testPopSingleElement() {
            stringStack.push("only");
            assertEquals("only", stringStack.pop());
        }
        
        @Test
        @DisplayName("Pop from empty stack after removing all elements")
        void testPopAllElementsThenPopAgain() {
            stringStack.push("first");
            stringStack.push("second");
            
            stringStack.pop();
            stringStack.pop();
            
            NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                stringStack::pop
            );
            assertEquals("Stack is empty, cannot pop", thrown.getMessage());
        }
        
        @Test
        @DisplayName("Pop returns elements in LIFO order")
        void testPopLIFOOrder() {
            stringStack.push("1");
            stringStack.push("2");
            stringStack.push("3");
            stringStack.push("4");
            stringStack.push("5");
            
            assertEquals("5", stringStack.pop());
            assertEquals("4", stringStack.pop());
            assertEquals("3", stringStack.pop());
            assertEquals("2", stringStack.pop());
            assertEquals("1", stringStack.pop());
        }
        
        @Test
        @DisplayName("Pop null element")
        void testPopNull() {
            stringStack.push(null);
            assertNull(stringStack.pop());
        }
        
        @Test
        @DisplayName("Pop after alternating push and pop operations")
        void testAlternatingPushPop() {
            stringStack.push("a");
            assertEquals("a", stringStack.pop());
            
            stringStack.push("b");
            stringStack.push("c");
            assertEquals("c", stringStack.pop());
            
            stringStack.push("d");
            assertEquals("d", stringStack.pop());
            assertEquals("b", stringStack.pop());
        }
    }
    
    // ======================== STATE AND INTERACTION TESTS ========================
    
    @Nested
    @DisplayName("Stack State and Multi-Operation Tests")
    class StateTests {
        
        @Test
        @DisplayName("Stack state changes correctly after push and pop sequence")
        void testStackStateChanges() {
            // Initial state: empty
            assertThrows(NoSuchElementException.class, stringStack::pop);
            
            // After push
            stringStack.push("item1");
            // Verify by popping
            assertEquals("item1", stringStack.pop());
            
            // Back to empty
            assertThrows(NoSuchElementException.class, stringStack::pop);
            
            // Multiple items
            stringStack.push("x");
            stringStack.push("y");
            stringStack.push("z");
            
            assertEquals("z", stringStack.pop());
            stringStack.push("w");
            
            assertEquals("w", stringStack.pop());
            assertEquals("y", stringStack.pop());
            assertEquals("x", stringStack.pop());
        }
        
        @Test
        @DisplayName("Large number of operations maintains correctness")
        void testLargeNumberOfOperations() {
            int count = 1000;
            
            // Push many elements
            for (int i = 0; i < count; i++) {
                integerStack.push(i);
            }
            
            // Pop all in reverse order
            for (int i = count - 1; i >= 0; i--) {
                assertEquals(i, integerStack.pop());
            }
            
            // Stack should be empty
            assertThrows(NoSuchElementException.class, integerStack::pop);
        }
        
        @Test
        @DisplayName("Push after pop from single-element stack")
        void testPushAfterPopFromSingleElement() {
            stringStack.push("first");
            assertEquals("first", stringStack.pop());
            
            stringStack.push("second");
            assertEquals("second", stringStack.pop());
        }
        
        @Test
        @DisplayName("Mixed null and non-null elements")
        void testMixedNullAndNonNullElements() {
            stringStack.push("value");
            stringStack.push(null);
            stringStack.push("another");
            stringStack.push(null);
            
            assertNull(stringStack.pop());
            assertEquals("another", stringStack.pop());
            assertNull(stringStack.pop());
            assertEquals("value", stringStack.pop());
        }
        
        @Test
        @DisplayName("Push and pop with different data types")
        void testDifferentDataTypes() {
            Stack<Object> mixedStack = new Stack<>();
            
            mixedStack.push("string");
            mixedStack.push(42);
            mixedStack.push(3.14);
            mixedStack.push(true);
            
            assertEquals(true, mixedStack.pop());
            assertEquals(3.14, mixedStack.pop());
            assertEquals(42, mixedStack.pop());
            assertEquals("string", mixedStack.pop());
        }
    }
    
    // ======================== BOUNDARY AND EDGE CASE TESTS ========================
    
    @Nested
    @DisplayName("Boundary and Edge Case Tests")
    class BoundaryTests {
        
        @Test
        @DisplayName("Stack with capacity of 1")
        void testStackWithCapacityOne() {
            Stack<String> stack = new Stack<>(1);
            stack.push("only");
            assertEquals("only", stack.pop());
        }
        
        @Test
        @DisplayName("Push and pop at capacity boundary")
        void testOperationsAtCapacityBoundary() {
            Stack<String> stack = new Stack<>(3);
            stack.push("a");
            stack.push("b");
            stack.push("c");
            
            // At capacity
            assertEquals("c", stack.pop());
            assertEquals("b", stack.pop());
            assertEquals("a", stack.pop());
        }
        
        @Test
        @DisplayName("Push beyond capacity and verify LIFO still works")
        void testPushBeyondCapacityLIFO() {
            Stack<String> stack = new Stack<>(2);
            stack.push("1");
            stack.push("2");
            stack.push("3");  // Exceeds capacity
            
            assertEquals("3", stack.pop());
            assertEquals("2", stack.pop());
            assertEquals("1", stack.pop());
        }
        
        @Test
        @DisplayName("Special characters in strings")
        void testSpecialCharacters() {
            stringStack.push("!@#$%^&*()");
            stringStack.push("\n\t\r");
            stringStack.push("\"quoted\"");
            
            assertEquals("\"quoted\"", stringStack.pop());
            assertEquals("\n\t\r", stringStack.pop());
            assertEquals("!@#$%^&*()", stringStack.pop());
        }
        
        @Test
        @DisplayName("Unicode and multi-byte characters")
        void testUnicodeCharacters() {
            stringStack.push("你好");
            stringStack.push("🚀");
            stringStack.push("Ñoño");
            
            assertEquals("Ñoño", stringStack.pop());
            assertEquals("🚀", stringStack.pop());
            assertEquals("你好", stringStack.pop());
        }
        
        @Test
        @DisplayName("Very long strings")
        void testVeryLongString() {
            String longString = "x".repeat(10000);
            stringStack.push(longString);
            assertEquals(longString, stringStack.pop());
        }
    }
    
    // ======================== EXCEPTION MESSAGE TESTS ========================
    
    @Nested
    @DisplayName("Exception Tests")
    class ExceptionTests {
        
        @Test
        @DisplayName("Pop from empty stack throws correct exception type")
        void testPopEmptyStackExceptionType() {
            assertThrows(
                NoSuchElementException.class,
                stringStack::pop
            );
        }
        
        @Test
        @DisplayName("Pop from empty stack has correct message")
        void testPopEmptyStackExceptionMessage() {
            NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                stringStack::pop
            );
            assertEquals("Stack is empty, cannot pop", exception.getMessage());
        }
        
        @Test
        @DisplayName("Multiple pops from empty stack all throw exception")
        void testMultiplePopsFromEmptyStack() {
            assertThrows(NoSuchElementException.class, stringStack::pop);
            assertThrows(NoSuchElementException.class, stringStack::pop);
            assertThrows(NoSuchElementException.class, stringStack::pop);
        }
    }
    
    // ======================== TYPE SAFETY TESTS ========================
    
    @Nested
    @DisplayName("Type Safety Tests")
    class TypeSafetyTests {
        
        @Test
        @DisplayName("String stack maintains type safety")
        void testStringStackTypeSafety() {
            stringStack.push("string1");
            stringStack.push("string2");
            
            String popped = stringStack.pop();
            assertInstanceOf(String.class, popped);
            assertEquals("string2", popped);
        }
        
        @Test
        @DisplayName("Integer stack maintains type safety")
        void testIntegerStackTypeSafety() {
            integerStack.push(1);
            integerStack.push(2);
            
            Integer popped = integerStack.pop();
            assertInstanceOf(Integer.class, popped);
            assertEquals(2, popped);
        }
    }
}
