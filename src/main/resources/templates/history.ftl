<#import "template.ftl" as layout />
<@layout.mainLayout>
    <div class="container">
        <div class="row">
            <h3>History of ${name}</h3>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <h3>All days ${count}</h3>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <h3>Last 100 days</h3>
        </div>
    </div>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Time Use</th>
        </tr>
        </thead>
        <tbody>
        <#list history as day>
            <tr>
                <td>${day.day.toString('HH:mm:ss dd.MM.yy')}</td>
                <td>${day.minutes}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</@layout.mainLayout>