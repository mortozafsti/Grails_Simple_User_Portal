<meta name="layout" content="public"/>

<div class="card">
    <div class="card-header">
        <b>
            Change Password
        </b>
    </div>
    <div class="card-body">
        <g:form controller="authentication" action="doChangePassword">
            <g:hiddenField name="id" value="${member.id}"/>
            <g:render template="/member/changepasswordform"/>
            <g:submitButton name="doChangePassword" value="Change" class="btn btn-primary"/>
            <g:link controller="member" action="index" class="btn btn-primary"><g:message code="cancel"/></g:link>
        </g:form>
    </div>
</div>