/**
 * SnapGames
 * 
 * Game Development Java
 * 
 * gdj107
 * 
 * @year 2017
 */
package com.snapgames.gdj.core.entity.tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snapgames.gdj.core.gfx.ImageUtils;

/**
 * 
 * @author Frédéric Delorme
 *
 */
public class TileSetTemplateGenerator {

	private static final Logger logger = LoggerFactory.getLogger(TileSetTemplateGenerator.class);

	/**
	 * Create a template image to build a TileSet.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Options options = buildOptions();
		try {
			CommandLineParser clp = new DefaultParser();
			CommandLine line = clp.parse(options, args);
			int width = Integer.parseInt(line.getOptionValue("width", "320"));
			int height = Integer.parseInt(line.getOptionValue("height", "240"));
			int tw = Integer.parseInt(line.getOptionValue("tilewidth", "16"));
			int th = Integer.parseInt(line.getOptionValue("tileheight", "16"));
			String filename = line.getOptionValue("", "template_" + width + "x" + height + "_" + tw + "x" + th);
			logger.info(options.toString());
			TileSetTemplateGenerator.gerenateImage(filename, width, height, tw, th);
			logger.info("write TileSet template '{}' to {}x{} with tile {}x{}",filename,width,height,tw,th);
			
			
		} catch (ParseException e) {
			logger.error("unable to parse command line. Try executing command this the -help option.");
		}

	}

	private static void gerenateImage(String fileName, int width, int height, int tw, int th) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = GraphicsEnvironment.getLocalGraphicsEnvironment().createGraphics(img);

		Font f = g.getFont().deriveFont(9.0f);
		g.setFont(f);
		
		g.setColor(Color.BLACK);
		g.clearRect(0, 0, width, height);
		int idx=0;
		for (int iy = 0; iy < height; iy += th) {
			for (int ix = 0; ix < width; ix += tw) {
				g.setColor(Color.GRAY);
				g.drawRect(ix, iy, tw, th);
				g.setColor(Color.WHITE);
				g.drawLine(ix, iy, ix,iy);
				g.setColor(Color.DARK_GRAY);
				g.drawString(String.format("%03d", idx++), ix+2, iy+16);
			}
		}

		ImageUtils.screenshot(img,fileName);
	}

	private static Options buildOptions() {
		Options options = new Options();

		// Declare options
		Option nOption = Option.builder("o").desc("output filname").longOpt("output").hasArg().build();
		Option wOption = Option.builder("w").desc("set width of template (default is 320 pixels)").longOpt("width")
				.hasArg().build();
		Option hOption = Option.builder("h").desc("set the height of template (default is 240 pixels)")
				.longOpt("height").hasArg().build();
		Option twOption = Option.builder("tw").desc("set width of tile (default is 16 pixels)").longOpt("tilewidth")
				.hasArg().build();
		Option thOption = Option.builder("th").desc("set the height of tile (default is 16 pixels)")
				.longOpt("tileheight").hasArg().build();
		// Add all these new possible options to the bundle :)
		options.addOption(nOption);
		options.addOption(wOption);
		options.addOption(hOption);
		options.addOption(twOption);
		options.addOption(thOption);
		return options;
	}

}
