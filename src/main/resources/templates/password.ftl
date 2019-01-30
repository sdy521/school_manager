<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>学生管理系统</title>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href = "/static/plugins/font-awesome-jquery-fontIconPicker/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/plugins/steps/jquery.steps.css" rel="stylesheet">
    <link href="/static/plugins/bootstrapFileUpload/css/fileinput.min.css" rel="stylesheet">
    <link href="/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">
</head>

<body class="gray-bg">

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-7">
            <div class="ibox-content">
                <div>
                    <h3>完善信息</h3>
                </div>
                <div class="row">
                    <div class="ibox-content">
                        <form id="form" class="wizard-big">
                            <h1>初始化密码</h1>
                            <fieldset>
                                <div class="row">
                                    <div class="col-lg-8">
                                        <div class="form-group">
                                            <label>用户名 *</label>
                                            <input id="userName" name="userName" type="text" class="form-control required">
                                        </div>
                                        <div class="form-group">
                                            <label>旧密码 *</label>
                                            <input id="oldPassword" name="oldPassword" type="password" class="form-control required">
                                        </div>
                                        <div class="form-group">
                                            <label>新密码 *</label>
                                            <input id="newPassword" name="newPassword" type="password" class="form-control required">
                                        </div>
                                        <div class="form-group">
                                            <label>确认密码 *</label>
                                            <input id="confirmPassword" name="confirmPassword" type="password" class="form-control required">
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="text-center">
                                            <div style="margin-top: 60px">
                                                <h4>返回登录页</h4>
                                                <a href="/login">
                                                    <i class="fa fa-sign-in" style="font-size: 180px;color: #e5e5e5 "></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <h1>完善信息</h1>
                            <fieldset>
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>性别 *</label>
                                            <select id="sex" name="sex" class="form-control required">
                                                <option value="" selected>请选择性别</option>
                                                <option value="0" selected>女</option>
                                                <option value="1">男</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>年龄 *</label>
                                            <input id="age" name="age" type="number" class="form-control required">
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>手机号 *</label>
                                            <input id="phone" name="phone" type="text" class="form-control required">
                                        </div>
                                        <div class="form-group">
                                            <label>住址 *</label>
                                            <input id="address" name="address" type="text" class="form-control required">
                                        </div>
                                    </div>
                                </div>
                            </fieldset>

                            <h1>完成</h1>
                            <fieldset>
                                <h2>基本信息填写已完成</h2>
                                <label for="acceptTerms">完成后需重新登入.</label>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#--<form enctype="multipart/form-data">
    <div class="file-loading">
        <input id="kv-explorer" type="file" multiple>
    </div>
    <br>
    <div class="file-loading">
        <input id="file-0a" class="file" type="file" multiple data-min-file-count="1" data-theme="fas">
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="reset" class="btn btn-outline-secondary">Reset</button>
</form>-->
</body>
<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/plugins/steps/jquery.steps.min.js"></script>
<script src="/static/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="/static/plugins/bootstrapFileUpload/js/fileinput.min.js"></script>
<script src="/static/modular/tool.js"></script>
<script>
    $(document).ready(function(){
        // $("#wizard").steps();
        $("#form").steps({
            bodyTag: "fieldset",
            onStepChanging: function (event, currentIndex, newIndex)
            {
                // Always allow going backward even if the current step contains invalid fields!
                if (currentIndex > newIndex)
                {
                    return true;
                }

                // Forbid suppressing "Warning" step if the user is to young
                if (newIndex === 3 && Number($("#age").val()) < 18)
                {
                    return false;
                }

                var form = $(this);

                // Clean up if user went backward before
                if (currentIndex < newIndex)
                {
                    // To remove error styles
                    $(".body:eq(" + newIndex + ") label.error", form).remove();
                    $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
                }

                // Disable validation on fields that are disabled or hidden.
                form.validate().settings.ignore = ":disabled,:hidden";

                // Start validation; Prevent going forward if false
                return form.valid();
            },
            onStepChanged: function (event, currentIndex, priorIndex)
            {
                // Suppress (skip) "Warning" step if the user is old enough.
                if (currentIndex === 2 && Number($("#age").val()) >= 18)
                {
                    $(this).steps("next");
                }

                // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
                if (currentIndex === 2 && priorIndex === 3)
                {
                    $(this).steps("previous");
                }
            },
            onFinishing: function (event, currentIndex)
            {
                var form = $(this);

                // Disable validation on fields that are disabled.
                // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
                form.validate().settings.ignore = ":disabled";

                // Start validation; Prevent form submission if false
                return form.valid();
            },
            onFinished: function (event, currentIndex)
            {
                // var form = $(this);
                // form.submit();
                var username = $("#userName").val();
                var oldPassword = $("#oldPassword").val();
                var newPassword = $("#newPassword").val();
                var repeatNewPassword = $("#confirmPassword").val();
                var sex = $("#sex").val();
                var phone = $("#phone").val();
                var address = $("#address").val();
                var age = $("#age").val();
                $.ajax({
                    url: "/perfectInfo",
                    type: 'POST',
                    data: JSON.stringify({
                        username:username,
                        oldPassword:oldPassword,
                        newPassword:newPassword,
                        repeatNewPassword:repeatNewPassword,
                        sex:sex,
                        phone:phone,
                        address:address,
                        age:age
                    }),
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success: function (r) {
                        if (r.code === 0) {
                            success("修改密码成功");
                            window.location.href = ("/login");
                        }
                        else if (r.code === 405) {
                            info("两次密码输入不一致");
                        }
                        else if (r.code === 406) {
                            info("旧密码输入错误");
                        }
                        else if (r.code === 407) {
                            info("用户不存在");
                        }
                    }
                })
            }
        }).validate({
            errorPlacement: function (error, element)
            {
                element.before(error);
            },
            rules: {
                confirmPassword: {
                    equalTo: "#newPassword"
                },
                phone: {
                    required : true,
                    minlength : 11,
                    isMobile : true
                }
            },
            messages: {
                userName:{
                    required : "请输入用户名",
                },
                oldPassword:{
                    required : "请输入旧密码",
                },
                newPassword:{
                    required : "请输入新密码",
                },
                confirmPassword:{
                    required : "请二次确认密码",
                    equalTo : "两次密码不相同"
                },
                sex:{
                    required : "请输入性别"
                },
                age:{
                    required : "请输入年龄"
                },
                address:{
                    required : "请输入住址"
                },
                phone : {
                    required : "请输入手机号",
                    minlength : "不能小于11个字符",
                    isMobile : "请正确填写手机号码"
                }
            }
        });
        jQuery.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写手机号码");
    });
</script>

</html>
