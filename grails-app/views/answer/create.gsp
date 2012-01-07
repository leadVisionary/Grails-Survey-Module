

<%@ page import="com.visionarysoftwaresolutions.Answer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'answer.label', default: 'Answer')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${answerInstance}">
            <div class="errors">
                <g:renderErrors bean="${answerInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="text"><g:message code="answer.text.label" default="Text" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: answerInstance, field: 'text', 'errors')}">
                                    <g:textField name="text" value="${answerInstance?.text}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isCorrect"><g:message code="answer.isCorrect.label" default="Is Correct" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: answerInstance, field: 'isCorrect', 'errors')}">
                                    <g:checkBox name="isCorrect" value="${answerInstance?.isCorrect}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="question"><g:message code="answer.question.label" default="Question" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: answerInstance, field: 'question', 'errors')}">
                                    <g:select name="question.id" from="${com.visionarysoftwaresolutions.Question.list()}" optionKey="id" value="${answerInstance?.question?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
