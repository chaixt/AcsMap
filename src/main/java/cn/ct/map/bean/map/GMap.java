package cn.ct.map.bean.map;

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

import cn.ct.map.bean.IGDraw;
import cn.ct.map.bean.graphics.GRectangle;

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
	private MReferencePos center = new MReferencePos(0, 0);
	private double pxSize = 1;
	private ReferencedEnvelope mapBounds;

	protected ReferencedEnvelope updateMapBounds() {
		if(map != null){
			if(center != null && center.getCrs() == null){
				center.setCrs(map.getCoordinateReferenceSystem());
			}
			if(this.getWidth() > 0 && this.getHeight() > 0 & pxSize > 0 & center != null){
				double map_half_width = 0.5 * this.getWidth() / pxSize;
				double map_half_height = 0.5 * this.getHeight() / pxSize;
				mapBounds = new ReferencedEnvelope(center.getX() - map_half_width ,center.getX() + map_half_width, center.getY() - map_half_height, center.getY() + map_half_height, center.getCrs());
			} else{
				mapBounds = map.getMaxBounds();
			}
		}
		return mapBounds;
	}
	
	protected void updateGEnvelop() {
		if(map != null){
			if(pxSize > 0 & mapBounds != null){
				center = new MReferencePos(mapBounds.getMedian(0), mapBounds.getMedian(1), mapBounds.getCoordinateReferenceSystem());
				this.setWidth((int)(mapBounds.getWidth() * pxSize + 0.5));
				this.setHeight((int)(mapBounds.getHeight() * pxSize + 0.5));
			}
		}
	}
	
	public GMap() {
		super();
	}
	public GMap(GMap gmap) {
		super();
		this.map = gmap.map;
		this.center = gmap.center;
		this.pxSize = gmap.pxSize;
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
		if(this.map != null){
			this.map = map;
			updateMapBounds();
		}
	}

	public ReferencedEnvelope getMapBounds() {
		return new ReferencedEnvelope(mapBounds);
	}
	
	public void setMapBounds(ReferencedEnvelope mapBounds) {
		if(mapBounds != null){
			this.mapBounds = new ReferencedEnvelope(mapBounds);
			updateGEnvelop();
		}
	}

	public double getPxSize() {
		return pxSize;
	}

	public void setPxSize(double pxSize) {
		if(pxSize > 0){
			this.pxSize = pxSize;
			updateMapBounds();
		}
	}

	public void setCenter(MPos pos) {
		if(pos != null){
			if(this.map != null){
				this.center = new MReferencePos(pos, this.map.getCoordinateReferenceSystem());
				updateMapBounds();
			}else{
				this.center = new MReferencePos(pos);
			}
		}
	}
	public void setCenter(MReferencePos pos) {
		if(pos != null){
			this.center = pos;
			updateMapBounds();
		}
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
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(mapBounds == null){
			mapBounds = map.getMaxBounds();
		}
		GTRenderer render = new StreamingRenderer();
		render.setMapContent(map);
		render.paint(g2d, new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()), mapBounds);
		return g2d;
	}

	public MReferencePos getCenter() {
		return new MReferencePos(this.center);
	}

}
