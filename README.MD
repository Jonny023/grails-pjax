# Grails Pjax Demo

### pjax的优点
* 发送ajax请求
* 提升用户体验（浏览器不刷新）

### html

```html
<!doctype html>
<html>
<head>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <style>
    .main {
        width: 96%;
        margin: 25px auto;
    }

    #loading {
        display: none;
        position: absolute;
        top: 20px;
        left: 30%;
        z-index: 11
    }

    #container {
        height: 200px;
        line-height: 200px;
        font-size: 30px;
        text-align: center;
    }

    .list {
        list-style: none;
    }

    a {
        padding: 0px 15px;
        width: 120px;
        display: block;
        line-height: 40px;
        border-bottom: 1px solid #fff;
        background: #a7bcff;
        text-decoration: none;
        color: #fff;
    }

    a:hover {
        background: #ffa500;
    }

    .selected {
        background: #ffa500;
    }
    </style>
</head>

<body>

<div class="main">

    <div id="loading">加载中...</div>

    <div id="container">JQuery pjax数据加载${content}</div>

    <ul class="list">
        <li><a href="/index/index?type=1" ${type=="1"?"class=selected":""}>Java</a></li>
        <li><a href="/index/index?type=2" ${type=="2"?"class=selected":""}>JavaScript</a></li>
        <li><a href="/index/index?type=3" ${type=="3"?"class=selected":""}>Python</a></li>
    </ul>
</div>

<asset:javascript src="jquery-2.2.0.min.js"/>
<asset:javascript src="jquery.pjax.min.js"/>

</body>
</html>
```

### JavaScript

```javascript
<script>
    $(function () {
        // {fragment: '#container', timeout: 1000}这个必须要，不然pjax无效果，而且发送两次请求
        $(document).pjax('.list a', '#container', {fragment: '#container', timeout: 1000});
        // $(document).pjax('.list a', '#container', {fragment: '#container', timeout: 1000});
        $(document).on('pjax:send', function () {
            $('#loading').show(300);
        });
        $(document).on('pjax:complete', function () {
            $('#loading').hide(300);
        });
        $("a").each(function (i, v) {
            $(v).on("click", function () {
                $("a").removeClass('selected');
                $(this).addClass("selected");
            });
        });
    });
</script>
```

### 注意
* 必须加上`{fragment: '#container', timeout: 1000}`延时，不然会发送两次请求且pjax不生效

