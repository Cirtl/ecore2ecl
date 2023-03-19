config {
    starter: <#if starter??>${starter}</#if>
    mono: <#if mono??>${mono}</#if>
    color: <#if color??>${color}</#if>
}

entities(<#list entities as entity>${entity.name}<#sep>, </#sep></#list>)

<#list entities as entity>
${entity.name}<#if (entity.extend)??> extends ${entity.extend}</#if><#if (entity.acts)??> acts <#list entity.acts as act>${act}<#sep>, </#sep></#list></#if> {
    <#if (entity.attributes)??>
    attributes: {
    <#list entity.attributes as attribute>
        ${attribute.name}: ${attribute.type}<#sep>,</#sep>
    </#list>
    }
    </#if>
    <#list entity.connections_group as connections>
    connections: {
    <#list connections as connection>
        ${connection.direction} <#if connection.lowerBound==connection.upperBound>${connection.lowerBound}<#else>${connection.lowerBound}..<#if connection.upperBound!=-1>${connection.upperBound}</#if></#if> (<#list connection.targets as target>${target}<#sep>, </#sep></#list>)
    </#list>
    }
    </#list>
    functions: {
        // auto generated
    }
    visualization: {
        // auto generated
    }
}
</#list>