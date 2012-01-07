<label for="answer${counter}.text">
  ${message(code:'answer.text.label', args:[counter])}
</label>
<g:textField name="answer${counter}.text" />
<label for="answer[${counter}].correct">
  ${message(code:'answer.correct.label')}
</label>
<g:checkBox name="answer${counter}.correct" />
