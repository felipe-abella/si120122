<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<html>
<head>
<title>Welcome</title>
</head>
<body>

	<h:form>
		<h:outputText value="New link to post: " />
		<h:inputText value="#{linkCreator.str}" />
		<h:commandButton action="interface" value="Post"
			actionListener="#{timeline.postEvent}" />
		<br>
		<h:outputText value="#{timeline.errorMessage}" />
	</h:form>
	<br>
	<table border="1">
		<tr>
			<td><h:form>
					<h:commandButton action="interface" value="Older posts"
						actionListener="#{timeline.olderPostsEvent}" />
					<h:outputText value="#{timeline.showingRangeStr }" />
					<h:commandButton action="interface" value="Newer posts"
						actionListener="#{timeline.newerPostsEvent}" />
					<br>
				</h:form> <h:outputText value="#{timeline.lastPostsStr}" escape="false" /></td>
			<td><br> Average post time: <h:outputText
					value="#{timeline.averagePostTimeStr}" /> <br> Last post
				time: <h:outputText value="#{timeline.lastPostTimeStr}" /> <br>
				Most common website: <h:outputText
					value="#{timeline.mostCommonWebsite}" /> <br></td>
		</tr>
	</table>

</body>
	</html>
</f:view>

