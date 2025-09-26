<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset = UTF-8">
    <script src="https://yastatic.net/jquery/2.2.3/jquery.min.js"></script>
    <link type="text/css" rel="stylesheet" href="https://yastatic.net/bootstrap/3.3.6/css/bootstrap.min.css"/>
    <script src="https://yastatic.net/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link type="text/css" rel="stylesheet" href="https://yastatic.net/highlightjs/8.2/styles/github.min.css"/>
    <script src="https://yastatic.net/highlightjs/8.2/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<div>
    <pre><code>Status code: <#if data.responseCode??>${data.responseCode}<#else>Unknown</#if></code></pre>
</div>

<#if data.url??>
    <div>
        <pre><code>Response URL: ${data.url}</code></pre>
    </div>
</#if>

<#if data.headers??>
    <h4>Headers</h4>
    <div>
        <#list data.headers as name, value>
            <div>
                <pre><code><b>${name}</b>: ${value}</code></pre>
            </div>
        </#list>
    </div>
</#if>

<#if data.body??>
    <h4>Body</h4>
    <div>
        <pre><code class="json">${data.body}</code></pre>
    </div>
</#if>

<#if data.time??>
    <h4>Response Time</h4>
    <div>
        <pre><code>${data.time} ms</code></pre>
    </div>
</#if>
</body>
</html>