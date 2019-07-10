<#import "template.ftl" as layout />
<@layout.mainLayout>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list employees as emp>
            <#if idEdit==emp.id>
                <form action="/edit" method="post">

                    <input type="hidden" id="id" name="id" value="${(emp.id)!}">
                    <tr>
                        <td>${emp.id}</td>
                        <td>
                            <div class="form-group">
                                <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name"
                                       value="${(emp.name)!}">
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="Enter Email"
                                       value="${(emp.email)!}">
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <input type="text" class="form-control" id="city" name="city" placeholder="Enter City"
                                       value="${(emp.city)!}">
                            </div>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </td>
                    </tr>
                </form>

            <#else >
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.name}</td>
                    <td>${emp.email}</td>
                    <td>${emp.city}</td>
                    <td>
                        <a href="/edit?id=${emp.id}" class="btn btn-secondary float-right mr-2"
                           role="button">Edit</a>
                        <a href="/delete?id=${emp.id}" class="btn btn-danger float-right mr-2" role="button">Delete</a>
                    </td>
                </tr>
            </#if>

        </#list>
        </tbody>
    </table>

    <div class="container">
        <div class="row">
            <h3>New Employee</h3>
        </div>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">City</th>
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
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="Enter Email"
                               value="">
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <input type="text" class="form-control" id="city" name="city" placeholder="Enter City"
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