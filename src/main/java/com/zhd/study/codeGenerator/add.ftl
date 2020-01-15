${r'<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin平台系统</title>
    <link th:href="@{/bower_components/bootstrap/dist/css/bootstrap.min.css}" rel="stylesheet">
    <link th:href="@{/bower_components/metisMenu/dist/metisMenu.min.css}" rel="stylesheet">
    <link th:href="@{/bower_components/morrisjs/morris.css}" rel="stylesheet">
    <link th:href="@{/bower_components/font-awesome/css/font-awesome.min.css}" rel="stylesheet" type="text/css">
    <link th:href="@{/myjs/bootstrap-select.css}" rel="stylesheet" type="text/css">

    <style type="text/css">

        td {
            padding: 2px;
            marign: 1px;
        }

    </style>
'}
    <script type="text/javascript">
        function add() {

            $("#${tableEntityName}Form").submit();
        }

        function cancel() {
            location.href = "/${tableEntityName}/queryAll${firstUpTableName}";
        }


    </script>


</head>
<body>
<div id="content" style="margin-top: 2em;">
    <div class="container-fluid">
        <form th:action="@{/${tableEntityName}/add}" method="POST" id="${tableEntityName}Form">
            <div style="text-align: center; width: 100%">
                <table style="width: 800px">
                    <#list columnMap?keys as key >
                    <tr>
                        <td width="30%"> ${columnMap[key]}<span style="color: red">*</span></td>
                        <td width="70%">
                        <input class="form-control" id="${key}" maxlength="60" name="${tableEntityName}.${key}"/>
                        </td>
                    </tr>
                    </#list>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="2" width="100%" align="center">
                            <table>
                                <tr>
                                    <td>
                                        <button type="button" onclick="add()" class="btn btn-default">提交</button>
                                    </td>
                                    <td>
                                        <button type="reset" onclick="cancel()" class="btn btn-default">取消</button>
                                    </td>
                                </tr>
                            </table>
                        </td>

                    </tr>
                </table>
            </div>
        </form>
    </div>
</div>
</body>
${r'
<!-- jQuery -->
<script th:src="@{/bower_components/jquery/dist/jquery.min.js}"></script>

<!-- Bootstrap Core JavaScript -->
<script th:src="@{/bower_components/bootstrap/dist/js/bootstrap.min.js}"></script>

<!-- Metis Menu Plugin JavaScript -->
<script th:src="@{/bower_components/metisMenu/dist/metisMenu.min.js}"></script>

<!-- Morris Charts JavaScript -->
<script th:src="@{/bower_components/raphael/raphael-min.js}"></script>


<script th:src="@{/myjs/confirm.js}"></script>

<script th:src="@{/myjs/bootstrap-select.js}"></script>


</html>
'}
