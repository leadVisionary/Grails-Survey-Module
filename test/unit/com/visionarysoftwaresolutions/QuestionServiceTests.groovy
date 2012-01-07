package com.visionarysoftwaresolutions

import grails.test.*
import org.springframework.context.MessageSource
import org.springframework.context.support.ResourceBundleMessageSource

class QuestionServiceTests extends GrailsUnitTestCase {
    QuestionService toTest = new QuestionService()
    
    String questionText = "Who's awesome"
    List choices = [ 
                     [ "text" : "You" , "correct" : "" ], 
                     [ "text" : "Me", "correct" : "on"],
                     [ "text" : "Someone else", "correct" : ""],
                   ]
    protected void setUp() {
        super.setUp()
        mockDomain(Question)
        mockDomain(Answer)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateValidMultipleChoiceQuestion() {
        def result = toTest.createMultipleChoiceQuestion(questionText, choices)
        assertTrue result.validate()
        assertTrue Question.list().size() == 1
        assertTrue Question.get(1).answers.size() == 3
    }
    
    void testCreateInvalidMultipleChoiceQuestionWithBlankQuestionText(){
        shouldFail(QuestionException){
            def result = toTest.createMultipleChoiceQuestion("", choices)
        }
    }
    
    void testCreateInvalidMultipleChoiceQuestionWithBlankAnswerText(){
        choices[1].text = ""
        shouldFail(AnswerException){
            def result = toTest.createMultipleChoiceQuestion(questionText, choices)
        }
    }
}
