package cn.ct.map;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.charset.Charset;

import org.geotools.coverage.grid.io.AbstractGridCoverage2DReader;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.store.ContentFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.renderer.GTRenderer;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.styling.StyleFactory;
import org.opengis.filter.FilterFactory2;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * 对MapContent进行封装
 *
 * @since 0.1
 *
 * @source $URL$
 * @version $Id$
 * @author 柴向停
 */
public class MapHelper {

	private static StyleFactory sf = CommonFactoryFinder.getStyleFactory();
	private static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

	public static StyleFactory getSF() {
		return sf;
	}

	public static FilterFactory2 getFilterFactory2() {
		return ff;
	}

	private MapContent map = new MapContent();

	public boolean addRaster(String raster_path) {
		return addRaster(raster_path,
				SLD.wrapSymbolizers(sf.getDefaultRasterSymbolizer()));
	}

	public boolean addRaster(String raster_path, Style style) {
		try {
			AbstractGridFormat format = GridFormatFinder
					.findFormat(raster_path);
			Hints hints = new Hints();
			if (format instanceof GeoTiffFormat) {
				hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER,
						Boolean.TRUE);
			}
			AbstractGridCoverage2DReader reader = format.getReader(raster_path,
					hints);
			Layer rasterLayer = new GridReaderLayer(reader, style);
			return map.addLayer(rasterLayer);
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}
	}
	public boolean addShp(String shp_path, Style style){
		return addShp(shp_path, style, Charset.defaultCharset());
	}
	public boolean addShp(String shp_path, Style style, Charset charset) {
		try {
			ShapefileDataStore sds = new ShapefileDataStore(new File(shp_path)
					.toURI().toURL());
			sds.setCharset(charset);
			ContentFeatureSource src = sds.getFeatureSource();
			if (style == null) {
				src.getSchema().getGeometryDescriptor().getName();
			}
			FeatureLayer flyr = new FeatureLayer(src, style);
			return map.addLayer(flyr);
		} catch (Throwable t) {
			t.printStackTrace();
			return false;
		}
	}

	public BufferedImage toBImg(BufferedImage bimg, Rectangle imgBounds) {
		return toBImg(bimg, imgBounds, map.getMaxBounds());
	}

	public BufferedImage toBImg(BufferedImage bimg) {
		return toBImg(bimg,
				new Rectangle(0, 0, bimg.getWidth(), bimg.getHeight()),
				map.getMaxBounds());
	}

	public BufferedImage toBImg(Rectangle imgBounds) {
		return toBImg(imgBounds, BufferedImage.TYPE_INT_RGB);
	}

	public BufferedImage toBImg(Rectangle imgBounds, int imgType) {
		return toBImg(new BufferedImage((int) imgBounds.getWidth(),
				(int) imgBounds.getHeight(), imgType), imgBounds,
				map.getMaxBounds());
	}
	

	public BufferedImage toBImg(BufferedImage bimg,
			ReferencedEnvelope mapBounds) {
		return toBImg(bimg,
				new Rectangle(0, 0, bimg.getWidth(), bimg.getHeight()),
				mapBounds);
	}

	public ReferencedEnvelope getMaxBounds(){
		return map.getMaxBounds();
	}
	
	public BufferedImage toBImg(Rectangle imgBounds,
			ReferencedEnvelope mapBounds) {
		return toBImg(imgBounds, BufferedImage.TYPE_INT_RGB, mapBounds);
	}
	
	public CoordinateReferenceSystem getCRS(){
		return map.getCoordinateReferenceSystem();
	}

	public BufferedImage toBImg(Rectangle imgBounds, int imgType,
			ReferencedEnvelope mapBounds) {
		return toBImg(new BufferedImage((int) imgBounds.getWidth(),
				(int) imgBounds.getHeight(), imgType), imgBounds,
				mapBounds);
	}

	public BufferedImage toBImg(BufferedImage bimg, Rectangle imgBounds,
			ReferencedEnvelope mapBounds) {
		bimg = bimg == null ? new BufferedImage(imgBounds.width,
				imgBounds.height, BufferedImage.TYPE_INT_RGB) : bimg;
		Graphics2D g2d = bimg.createGraphics();
		GTRenderer render = new StreamingRenderer();
		render.setMapContent(map);
		System.out.println(map.getMaxBounds());
		System.out.println(map.getMaxBounds().getCoordinateReferenceSystem());
		System.out.println(mapBounds);
		System.out.println(mapBounds.getCoordinateReferenceSystem());
		render.paint(g2d, imgBounds, mapBounds);
		return bimg;
	}

}
