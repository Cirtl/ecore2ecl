package process.parser;

import process.parser.SSECL.SSECLBaseListener;
import process.parser.SSECL.SSECLParser;
import transform.data.SGenealogy;
import transform.data.SSData;
import transform.model.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class SSDataExtractor extends SSECLBaseListener {
    private final SGenealogy genealogy;

    public SSDataExtractor() {
        this.genealogy = new SGenealogy();
    }

    public SGenealogy getGenealogy() {
        return genealogy;
    }

    @Override
    public void enterConfiguration(SSECLParser.ConfigurationContext ctx) {
        this.genealogy.initConfig(ctx.starter.getText(), ctx.mono.getText(), ctx.color.getText());
    }

    @Override
    public void enterEntityState(SSECLParser.EntityStateContext ctx) {
        List<String> list = new ArrayList<>();
        ctx.entities.forEach(token -> list.add(token.getText()));
        genealogy.initSet(list);
    }

    @Override
    public void enterEntityDef(SSECLParser.EntityDefContext ctx) {
        // 获取实体继承属性
        List<String> parents = new ArrayList<>();
        if (ctx.extend != null) parents.add(ctx.extend.getText());

        List<String> elders = new ArrayList<>();
        if (!ctx.acts.isEmpty()) ctx.acts.forEach(token -> elders.add(token.getText()));

        // 获取实体连接属性
        List<List<SSData.Connection>> connectionsGroups = new ArrayList<>();
        ctx.connections.forEach(connectionDefContext -> {
            List<SSData.Connection> group = new ArrayList<>();
            connectionDefContext.connections.forEach(singleConnectionContext -> {
                List<String> targets = new ArrayList<>();
                singleConnectionContext.targets.forEach(target -> targets.add(target.getText()));
                int lower = Integer.parseInt(singleConnectionContext.lower.getText());
                int upper = (singleConnectionContext.upper == null ? -1 : Integer.parseInt(singleConnectionContext.upper.getText()));
                group.add(new SSData.Connection(
                    lower,
                    singleConnectionContext.range == null ? lower : upper,
                    targets,
                    singleConnectionContext.type.getText().equals("to")
                ));
            });
            connectionsGroups.add(group);
        });

        // 创建实体
        SSData entity = new SSData(
                ctx.name.getText(),
                parents,
                elders,
                connectionsGroups
        );

        // 设置实体编辑器相关属性
        entity.setFunctionsBlock(ctx.function.content.getText());
        entity.setVisualizationBlock(ctx.vis.content.getText());

        // 设置实体属性
        List<ModelAttribute> attributes = new ArrayList<>();
        for (int i = 0; i < ctx.attr.names.size(); i++) {
            attributes.add(new ModelAttribute(ctx.attr.names.get(i).getText(), ctx.attr.types.get(i).getText()));
        }
        entity.setAttributes(attributes);

        this.genealogy.addEntity(entity);
    }
}
