config {
    starter: ${starter}
    mono: ${mono}
    color: ${color}
}

entities(<#list entities as entity>${entity.name}<#sep>, </#sep></#list>)

<#list entities as entity>
${entity.name} <#if (entity.extend)??>extends ${entity.extend}</#if> <#if (entity.implements)??><#list entity.implements as implement>${implement}<#sep>, </#sep></#list></#if> {
    <#if (entity.attributes)??>
    attributes: {
    <#list entity.attributes as attribute>
        ${attribute.name}: ${attribute.type}<#sep>,</#sep>
    </#list>
    }
    </#if>
<#--  sapce for connections  -->
    functions: {
        // auto generated
        
    }
    visualization: {
        // auto generated

    }
}
</#list>