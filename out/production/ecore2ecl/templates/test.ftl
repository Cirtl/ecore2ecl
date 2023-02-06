begin preamble
    semantic stdSem
    visualization vizVisualization VIS ( starter Family, mono mpalette, color mpalette )
end

entities (<#list entities as entity>${entity.name}<#sep>, </#sep></#list>)

<#list entities as entity>
begin ${entity.name}
    semantic s

    begin connections
        connects
    end

    begin visualization VIS
        renders
        layout
        enable ()
    end
end

</#list>

