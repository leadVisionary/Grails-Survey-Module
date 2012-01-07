package com.visionarysoftwaresolutions

import grails.test.*

class QuestionTests extends GrailsUnitTestCase {
    Question toTest
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testValidQuestionWithOnlyText() {
        toTest= new Question(text:"Who's awesome?")
        mockForConstraintsTests(Question, [toTest])
        assertTrue toTest.validate()
    }
    
    void testInvalidNonUniqueText(){
        toTest= new Question(text:"Who's awesome?")
        Question another = new Question(text:"Who's awesome?")
        mockForConstraintsTests(Question, [toTest, another])
        assertFalse toTest.validate()
        assertFalse another.validate()
        assertEquals "unique", toTest.errors['text']
        assertEquals "unique", another.errors['text']
    }
    
    void testQuestionStringRepresentation(){
        toTest= new Question(text:"Who's awesome?")
        assertEquals "Who's awesome? - null", "${toTest}"
    }
}
