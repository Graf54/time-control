<#macro mainLayout title="List of processes">
    <!doctype html>
    <html lang="en">
    <head>
        <title>${title}</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
              crossorigin="anonymous">
    </head>
    <body>
    <div class="container">
        <div><a href="/close" title="Выключить программу" class="btn btn-danger float-right mr-2" role="button">Off</a></div>
        <div class="row m-1">
            <h3>Processes</h3>
        </div>
        <div class="row m-1">
            <#nested/>
        </div>
    </div>
    </body>
    </html>
</#macro>