package cn.ct.map.bean;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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

public class GMap extends GRectangle implements IGDraw {

	private static StyleFactory sf = CommonFactoryFinder.getStyleFactory();
	private static FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

	public static StyleFactory getSF() {
		return sf;
	}

	public static FilterFactory2 getFilterFactory2() {
		return ff;
	}

	private MapContent map = new MapContent();
	private ReferencedEnvelope mapBounds;

	public GMap() {
		super();
	}
	public GMap(GMap gmap) {
		super();
		this.map = gmap.map;
		this.mapBounds = gmap.mapBounds;
	}

	public GMap(MapContent map) {
		super();
		this.map = map;
	}

	public GMap(MapContent map, ReferencedEnvelope mapBounds) {
		super();
		this.map = map;
		this.mapBounds = mapBounds;
	}

	public MapContent getMap() {
		return map;
	}

	public void setMap(MapContent map) {
		this.map = map;
	}

	public ReferencedEnvelope getMapBounds() {
		return mapBounds;
	}

	public void setMapBounds(ReferencedEnvelope mapBounds) {
		this.mapBounds = mapBounds;
	}

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

	public ReferencedEnvelope getMaxBounds(){
		return map.getMaxBounds();
	}
	
	public CoordinateReferenceSystem getCRS(){
		return map.getCoordinateReferenceSystem();
	}

	@Override
	public Graphics2D draw(Graphics2D g2d) {
		if(mapBounds == null){
			mapBounds = map.getMaxBounds();
		}
		GTRenderer render = new StreamingRenderer();
		render.setMapContent(map);
		render.paint(g2d, new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()), mapBounds);
		return g2d;
	}

}
