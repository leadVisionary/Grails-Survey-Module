package com.visionarysoftwaresolutions

import grails.test.*

class QuestionControllerTests extends ControllerUnitTestCase {
    String questionText = "Who's awesome"
    List choices = [ 
                     [ "text" : "You" , "correct" : "" ], 
                     [ "text" : "Me", "correct" : "on"],
                     [ "text" : "Someone else", "correct" : ""],
                   ]
    def questionServiceControl
    
    protected void setUp() {
        super.setUp()
        mockDomain(Question)
        mockDomain(Answer)
        this.controller.params.questionText = questionText
        this.controller.params.answer1 = choices[0]
        this.controller.params.answer2 = choices[1]
        this.controller.params.answer3 = choices[2]
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateValidMultipleChoiceQuestion() {
        questionServiceControl = mockFor(QuestionService)
        questionServiceControl.demand.createMultipleChoiceQuestion(1..1){
            text, choices -> new Question(id:1, text:"boo")
        }
        this.controller.questionService = questionServiceControl.createMock()
        this.controller.createMultipleChoiceQuestion()
        assertEquals "show", this.controller.redirectArgs["action"]
        assertEquals "1", this.controller.redirectArgs["id"]
    }
    
    void testCreateInvalidMultipleChoiceQuestionWithBlankQuestionText(){
        this.controller.params.questionText = ""
        questionServiceControl = mockFor(QuestionService)
        questionServiceControl.demand.createMultipleChoiceQuestion(1..1){
            throw new QuestionException(message: "returning from test", problem: new Question(id:1, text:"boo"))
        }
        this.controller.questionService = questionServiceControl.createMock()
        shouldFail(QuestionException){
            this.controller.createMultipleChoiceQuestion()
        }
        assertEquals "index", this.controller.redirectArgs["action"]
        assertEquals "returning from test", this.controller.flash.message
        assertNotNull this.controller.flash.questionInstance
    }
    
    void testCreateInvalidMultipleChoiceQuestionWithBlankAnswerText(){
        this.controller.params.answer1 = ""
        questionServiceControl = mockFor(QuestionService)
        questionServiceControl.demand.createMultipleChoiceQuestion(1..1){
            throw new AnswerException(message: "returning from test", problem: new Answer(text:"boo", isCorrect:false, question:new Question(id:1,text:"loo")))
        }
        this.controller.questionService = questionServiceControl.createMock()
        shouldFail(AnswerException){
            this.controller.createMultipleChoiceQuestion()
        }
        assertEquals "index", this.controller.redirectArgs["action"]
        assertEquals "returning from test", this.controller.flash.message
        assertNotNull this.controller.flash.answerInstance
    }
}
