<%@include file="includes/header.jsp" %>

<form action="pages/newapplication" method="post">
    <div class="form-group">
        <label for="appname">Application name</label>
        <input class="form-control" id="appname" type="text" value="${application.getName()}" name="name">
    </div>
    <div class="form-group">
        <label for="appdesc">Description</label>
        <textarea class="form-control" id="appdesc" rows="5" name="description" id="description">${application.getDescription()}</textarea>
    </div>
    <input type="hidden" name="id" value="${id}">
    <button type="submit" class="btn btn-primary">Save</button>
</form>

<%@include file="includes/footer.jsp" %>
