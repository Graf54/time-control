<#import "template.ftl" as layout />
<@layout.mainLayout>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Created</th>
            <th scope="col">Time Limit</th>
            <th scope="col">Time Use</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list processes as proc>
            <#if idEdit==proc.id>
                <form action="/edit" method="post">

                    <input type="hidden" id="id" name="id" value="${(proc.id)!}">
                    <tr>
                        <td>${proc.id}</td>
                        <td>
                            <div class="form-group">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name"
                                       value="${(proc.name)!}">
                            </div>
                        </td>
                        <td>${proc.added}</td>
                        <td>
                            <div class="form-group">
                                <input type="number" class="form-control" id="timeLimit" name="timeLimit"
                                       placeholder="Enter Limit"
                                       value="${(proc.timeLimit)!}">
                            </div>
                        </td>
                        <td>${proc.timeUse}</td>
                        <td>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </td>
                    </tr>
                </form>

            <#else >
                <tr>
                    <td>${proc.id}</td>
                    <td>${proc.name}</td>
                    <td>${proc.added.toString('HH:mm:ss dd.MM.yy')}</td>
                    <td>${proc.timeLimit}</td>
                    <td>${proc.timeUse}</td>
                    <td>
                        <a href="/edit?id=${proc.id}" class="btn btn-secondary float-right mr-2"
                           role="button">Edit</a>
                        <a href="/delete?id=${proc.id}" class="btn btn-danger float-right mr-2" role="button">Delete</a>
                    </td>
                </tr>
            </#if>

        </#list>
        </tbody>
    </table>

    <div class="container">
        <div class="row">
            <h3>New process</h3>
        </div>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">TimeLimit</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <form action="/new" method="post">
            <tr>
                <td>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name"
                               value="">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="number" class="form-control" id="timeLimit" name="timeLimit"
                               placeholder="Enter time limit of day"
                               value="">
                    </div>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </td>
            </tr>
        </form>
        </tbody>
    </table>
</@layout.mainLayout>