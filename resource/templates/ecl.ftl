begin preamble
    semantic stdSem
    visualization vizVisualization VIS ( starter <#if starter??>${starter}</#if>, mono <#if mono??>${mono}</#if>, color <#if color??>${color}</#if> )
end

entities (<#list entities as entity>${entity.name}<#sep>, </#sep></#list>)

<#list entities as entity>
begin ${entity.name}
    semantic s

    <#list entity.connections_group as connections>
    begin connections
    <#list connections as connection>
        connects ${connection.direction} <#if connection.lowerBound==connection.upperBound>${connection.lowerBound}<#else>${connection.lowerBound}..<#if connection.upperBound!=-1>${connection.upperBound}</#if></#if> (<#list connection.targets as target>${target}<#sep>, </#sep></#list>)
    </#list>
    end
    </#list>
    <#if (entity.funcs)??>
    <#list entity.funcs as func>
    ${func}
    </#list>
    </#if>
    begin visualization VIS
        <#if (entity.vis_list)??>
        <#list entity.vis_list as vis>
        ${vis}
        </#list>
        </#if>
    end
end

</#list>

