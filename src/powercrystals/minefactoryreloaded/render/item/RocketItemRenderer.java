package powercrystals.minefactoryreloaded.render.item;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCOBJParser;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.SwapYZ;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.TRSRTransformation;
import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.entity.EntityRocket;
import powercrystals.minefactoryreloaded.render.entity.EntityRocketRenderer;

import java.util.Map;

public class RocketItemRenderer extends BaseItemRenderer {

	private static CCModel launcherModel;

	public RocketItemRenderer() {

		Map<String, CCModel> models = CCOBJParser.parseObjModels(new ResourceLocation(MineFactoryReloadedCore.modelFolder + "rocket.obj"), new SwapYZ());
		launcherModel = models.get("Tube");

		TRSRTransformation thirdPerson = TransformUtils.get(0, 0, 1, 90, 180, 0, 0.015f);
		ImmutableMap.Builder<ItemCameraTransforms.TransformType, TRSRTransformation> builder = ImmutableMap.builder();
		builder.put(ItemCameraTransforms.TransformType.GUI, TransformUtils.get(0, -1, 0, 30, 135, 0, 0.015f));
		builder.put(ItemCameraTransforms.TransformType.GROUND, TransformUtils.get(0, 3, 0, 0, 0, 0, 0.01f));
		builder.put(ItemCameraTransforms.TransformType.FIXED, TransformUtils.get(0, 0, 0, 0, 90, 0, 0.03f));
		builder.put(ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
		builder.put(ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, TransformUtils.leftify(thirdPerson));
		builder.put(ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND, TransformUtils.get(0, -1, 0, 8, 190, 0, 0.025f));
		builder.put(ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND, TransformUtils.get(0, -1, 0, 8, 190, 0, 0.025f));
		transformations = builder.build();
	}

	@Override
	protected void drawModel(CCRenderState ccrs, ItemStack stack) {

		TextureUtils.changeTexture(EntityRocketRenderer.rocket);
		ccrs.startDrawing(4, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

		launcherModel.render(ccrs);

		ccrs.draw();
	}
}
