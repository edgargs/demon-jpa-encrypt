<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'warrant.label', default: 'Warrant')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-warrant" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-warrant" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:with bean="warrant">
                <div class="fieldcontain">
                    <label><g:message code="warrant.liid.label"/></label>
                    <f:display property="liid"/>
                </div>
                <!--div class="fieldcontain">
                    <label><g:message code="warrant.targetid_type.label"/></label>
                    <f:display property="targetid_type"/>
                </div-->
                <div class="fieldcontain">
                    <label><g:message code="warrant.msisdn.label"/></label>
                    <f:display property="msisdn"/>
                </div>
                <!--div class="fieldcontain">
                    <label><g:message code="warrant.imei.label"/></label>
                    <f:display property="imei"/>
                </div-->
                <div class="fieldcontain">
                    <label><g:message code="warrant.warrant_date.label"/></label>
                    <f:display property="warrant_date"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.reference_name.label"/></label>
                    <f:display property="reference_name"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.li_type.label"/></label>
                    <f:display property="li_type"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.begin_datetime.label"/></label>
                    <f:display property="begin_datetime"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.end_datetime.label"/></label>
                    <f:display property="end_datetime"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.lemf_ip.label"/></label>
                    <f:display property="lemf_ip"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.lemf_port.label"/></label>
                    <f:display property="lemf_port"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.ftp_user.label"/></label>
                    <f:display property="ftp_user"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.ftp_pass.label"/></label>
                    <f:display property="ftp_pass"/>
                </div>
                <div class="fieldcontain">
                    <label><g:message code="warrant.observations.label"/></label>
                    <f:display property="observations"/>
                </div>

                <div class="fieldcontain">
                    <label><g:message code="warrant.period.label"/> (minutos)</label>
                    <f:display property="period"/>
                </div>
            </f:with>
            <g:form resource="${this.warrant}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.warrant}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
