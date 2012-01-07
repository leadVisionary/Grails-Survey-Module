<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create A Quiz</title>
  </head>
  <body>
  <g:if test="${flash.message}">
    <div id="message">
      ${flash.message}
    </div>
    <g:if test="${flash.answerInstance}">
    <g:hasErrors bean="${flash.answerInstance}">
            <div class="errors">
                <g:renderErrors bean="${flash.answerInstance}" as="list" />
            </div>
    </g:hasErrors>
    </g:if>
    <g:if test="${flash.questionInstance}">
    <g:hasErrors bean="${flash.questionInstance}">
            <div class="errors">
                <g:renderErrors bean="${flash.questionInstance}" as="list" />
            </div>
    </g:hasErrors>
    </g:if>
  </g:if>
    <h1>Let's create a multiple choice question!</h1>
    <g:form action="createMultipleChoiceQuestion">
      <label for="question">${message(code:'question.text.label')}</label>
      <g:textField name="questionText" />
      <g:each var="counter" in="${1..3}">
        <div id="answer${counter}">
          <g:render template="/question/answer" model="[counter : counter]" />
        </div>
      </g:each>
      <g:submitButton name="${message(code:'default.button.create.label')}" value="${message(code:'default.button.create.label')}" />
    </g:form>
  </body>
</html>
