begin preamble
    semantic stdSem
    visualization vizVisualization VIS ( starter , mono palette, color palette )
end

entities (<#list entities as entity>${entity.name}<#sep>, </#sep></#list>)

<#list entities as entity>
begin ${entity.name}
    semantic s

<#--    <#if entity.edge>-->
    begin connections
    <#list entity.connections as connection>
        connects ${connection.direction} <#if connection.single>${connection.number}<#else>${connection.lowerBound}..${connection.upperBound}</#if> (<#list connection.targets as target>${target}<#sep>, </#sep></#list>)
    </#list>
    end
<#--    <#else>-->
<#--    <#list >-->

<#--    </#list>-->
<#--    </#if>-->

    begin visualization VIS
        renders
        layout
        enable ()
    end
end

</#list>

