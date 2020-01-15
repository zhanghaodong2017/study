${r'<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin平台系统</title>

    <!-- Bootstrap Core CSS -->
    <link th:href="@{/bower_components/bootstrap/dist/css/bootstrap.min.css}" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link th:href="@{/bower_components/metisMenu/dist/metisMenu.min.css}" rel="stylesheet">

    <!-- Custom CSS -->
    <link th:href="@{/dist/css/sb-admin-2.css}" rel="stylesheet">

    <!-- Custom Fonts -->
    <link th:href="@{/bower_components/font-awesome/css/font-awesome.min.css}" rel="stylesheet" type="text/css">

    <link th:href="@{/bower_components/morrisjs/morris.css}" rel="stylesheet" type="text/css">

    <link th:href="@{/bower_components/font-awesome/css/font-awesome.min.css}" rel="stylesheet" type="text/css">

    <link th:href="@{/bower_components/datetimepicker/css/bootstrap-datetimepicker.css}" rel="stylesheet"
          type="text/css">

    <style>
        html, body {
            margin: 0;
            height: 100%;
            width: 100%;
        }
    </style>

'}
    <script type="text/javascript">
        function add${firstUpTableName}() {
            location.href = "/${tableEntityName}/showAdd";
        }

        function

        del${firstUpTableName}(guid, name)
        {
            $.MsgBox.Confirm("提示", "你要删除[" + name + "]吗？", function () {
                location.href = '/${tableEntityName}/delete?guid=' + guid;
            });

        }

        function update${firstUpTableName}(guid) {
            location.href = '/${tableEntityName}/showUpdate?guid=' + guid;
        }

    </script>

</head>

<body>

<div id="content" style="margin:0;">

    <div class="container-fluid">

        <div style="text-align: right;">
            <button type="button" class="btn btn-primary btn-lg"
                    onclick="add${firstUpTableName}()">新增
            </button>
        </div>

        <div class="panel-body">
            <div class="dataTable_wrapper">
                <table class="table table-striped table-bordered table-hover"
                       id="dataTables-example">

                    <thead>
                    <tr>
                        <#list columnCommentList as column>
                        <th>${column}</th>
                        </#list>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="${tableEntityName}: ${r'${'}${tableEntityName}List${r'}'}">
                        <#list columnList as column>
                        <td th:text="${r'${'}${tableEntityName}.${column}${r'}'}"></td>
                        </#list>

                        <td class="center">
                            <button type="button"
                                    class="btn btn-success"
                                    th:onclick="update${firstUpTableName}([[${r'${'}${tableEntityName}.guid${r'}'}]])">修改
                            </button>
                            <button type="button"
                                    class="btn btn-danger"
                                    th:onclick="del${firstUpTableName}([[${r'${'}${tableEntityName}.guid}]],[[${r'${'}${tableEntityName}.guid${r'}'}]])">删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
${r'

<!-- jQuery -->
<script th:src="@{/bower_components/jquery/dist/jquery.min.js}"></script>

<!-- Bootstrap Core JavaScript -->
<script th:src="@{/bower_components/bootstrap/dist/js/bootstrap.min.js}"></script>

<script th:src="@{/bower_components/datatables/media/js/jquery.dataTables.min.js}"></script>

<script th:src="@{/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js}"></script>

<script th:src="@{/bower_components/raphael/raphael-min.js}"></script>

<!-- Metis Menu Plugin JavaScript -->
<script th:src="@{/bower_components/metisMenu/dist/metisMenu.min.js}"></script>

<!-- Custom Theme JavaScript -->
<script th:src="@{/dist/js/sb-admin-2.js}"></script>

<script th:src="@{/bower_components/datetimepicker/js/bootstrap-datetimepicker.js}"></script>

<script th:src="@{/bower_components/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js}"></script>

<script th:src="@{/myjs/confirm.js}"></script>
</body>

<script type="text/javascript" th:inline="javascript">
    $(document).ready(function () {
        $("#dataTables-example").DataTable({
            responsive: true,
            "oLanguage": {
                "sUrl": "/myjs/zh_CN.json"
            }
        });
        $(".form_datetime").datetimepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayBtn: true,
            language: "zh-CN",
            pickerPosition: "bottom-left"
        });
    });

</script>

</html>

'}