<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'warrant.label', default: 'Warrant')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-warrant" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-warrant" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.warrant}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.warrant}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.warrant}" method="PUT">
                <g:hiddenField name="version" value="${this.warrant?.version}" />
                <fieldset class="form">
                    <f:with bean="warrant">
                        <f:field property="liid"/>
                        <!--f:field property="targetid_type"/-->
                        <f:field property="msisdn"/>
                        <!--f:field property="imei"/-->
                        <f:field property="warrant_date"/>
                        <f:field property="reference_name"/>
                        <f:field property="li_type"/>
                        <f:field property="begin_datetime">
                            <g:datePicker name="${property}" value="${value}"
                                      noSelection="['':'-Choose-']"/>
                        </f:field>
                        <f:field property="end_datetime">
                            <g:datePicker name="${property}" value="${value}"
                                          noSelection="['':'-Choose-']"/>
                        </f:field>
                        <f:field property="lemf_ip"/>
                        <f:field property="lemf_port"/>
                        <f:field property="ftp_user"/>
                        <f:field property="ftp_pass"/>
                        <f:field property="observations">
                            <g:textArea name="${property}" value="${value}" rows="5" cols="40"/>
                        </f:field>

                        <f:field property="period" label="${message(code: 'warrant.period.label', default: 'Update')} (minutos)"/>
                    </f:with>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
