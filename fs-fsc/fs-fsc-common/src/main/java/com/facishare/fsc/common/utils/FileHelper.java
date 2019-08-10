package com.facishare.fsc.common.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;

public class FileHelper {
  private static Map<String, String> Mappings = new HashMap<>();

  static {
    Mappings.put(".ez", "application/andrew-inset");
    Mappings.put(".anx", "application/annodex");
    Mappings.put(".atom", "application/atom+xml");
    Mappings.put(".atomcat", "application/atomcat+xml");
    Mappings.put(".atomsrv", "application/atomserv+xml");
    Mappings.put(".lin", "application/bbolin");
    Mappings.put(".cap", "application/cap");
    Mappings.put(".pcap", "application/cap");
    Mappings.put(".cu", "application/cu-seeme");
    Mappings.put(".davmount", "application/davmount+xml");
    Mappings.put(".tsp", "application/dsptype");
    Mappings.put(".es", "application/ecmascript");
    Mappings.put(".spl", "application/futuresplash");
    Mappings.put(".hta", "application/hta");
    Mappings.put(".jar", "application/java-archive");
    Mappings.put(".ser", "application/java-serialized-object");
    Mappings.put(".class", "application/java-vm");
    Mappings.put(".js", "application/javascript");
    Mappings.put(".m3g", "application/m3g");
    Mappings.put(".hqx", "application/mac-binhex40");
    Mappings.put(".cpt", "application/mac-compactpro");
    Mappings.put(".nb", "application/mathematica");
    Mappings.put(".nbp", "application/mathematica");
    Mappings.put(".mdb", "application/msaccess");
    Mappings.put(".doc", "application/msword");
    Mappings.put(".dot", "application/msword");
    Mappings.put(".mxf", "application/mxf");
    Mappings.put(".bin", "application/octet-stream");
    Mappings.put(".oda", "application/oda");
    Mappings.put(".ogx", "application/ogg");
    Mappings.put(".pdf", "application/pdf");
    Mappings.put(".key", "application/pgp-keys");
    Mappings.put(".pgp", "application/pgp-signature");
    Mappings.put(".prf", "application/pics-rules");
    Mappings.put(".ps", "application/postscript");
    Mappings.put(".ai", "application/postscript");
    Mappings.put(".eps", "application/postscript");
    Mappings.put(".epsi", "application/postscript");
    Mappings.put(".epsf", "application/postscript");
    Mappings.put(".eps2", "application/postscript");
    Mappings.put(".eps3", "application/postscript");
    Mappings.put(".rar", "application/rar");
    Mappings.put(".rdf", "application/rdf+xml");
    Mappings.put(".rss", "application/rss+xml");
    Mappings.put(".rtf", "application/rtf");
    Mappings.put(".smi", "application/smil");
    Mappings.put(".smil", "application/smil");
    Mappings.put(".xhtml", "application/xhtml+xml");
    Mappings.put(".xht", "application/xhtml+xml");
    Mappings.put(".xml", "application/xml");
    Mappings.put(".xsl", "application/xml");
    Mappings.put(".xsd", "application/xml");
    Mappings.put(".xspf", "application/xspf+xml");
    Mappings.put(".zip", "application/zip");
    Mappings.put(".apk", "application/vnd.android.package-archive");
    Mappings.put(".cdy", "application/vnd.cinderella");
    Mappings.put(".kml", "application/vnd.google-earth.kml+xml");
    Mappings.put(".kmz", "application/vnd.google-earth.kmz");
    Mappings.put(".xul", "application/vnd.mozilla.xul+xml");
    Mappings.put(".xls", "application/vnd.ms-excel");
    Mappings.put(".xlb", "application/vnd.ms-excel");
    Mappings.put(".xlt", "application/vnd.ms-excel");
    Mappings.put(".cat", "application/vnd.ms-pki.seccat");
    Mappings.put(".stl", "application/vnd.ms-pki.stl");
    Mappings.put(".ppt", "application/vnd.ms-powerpoint");
    Mappings.put(".pps", "application/vnd.ms-powerpoint");
    Mappings.put(".odc", "application/vnd.oasis.opendocument.chart");
    Mappings.put(".odb", "application/vnd.oasis.opendocument.database");
    Mappings.put(".odf", "application/vnd.oasis.opendocument.formula");
    Mappings.put(".odg", "application/vnd.oasis.opendocument.graphics");
    Mappings.put(".otg", "application/vnd.oasis.opendocument.graphics-template");
    Mappings.put(".odi", "application/vnd.oasis.opendocument.image");
    Mappings.put(".odp", "application/vnd.oasis.opendocument.presentation");
    Mappings.put(".otp", "application/vnd.oasis.opendocument.presentation-template");
    Mappings.put(".ods", "application/vnd.oasis.opendocument.spreadsheet");
    Mappings.put(".ots", "application/vnd.oasis.opendocument.spreadsheet-template");
    Mappings.put(".odt", "application/vnd.oasis.opendocument.text");
    Mappings.put(".odm", "application/vnd.oasis.opendocument.text-master");
    Mappings.put(".ott", "application/vnd.oasis.opendocument.text-template");
    Mappings.put(".oth", "application/vnd.oasis.opendocument.text-web");
    Mappings.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    Mappings.put(".xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
    Mappings.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
    Mappings.put(".ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
    Mappings.put(".potx", "application/vnd.openxmlformats-officedocument.presentationml.template");
    Mappings.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    Mappings.put(".dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
    Mappings.put(".cod", "application/vnd.rim.cod");
    Mappings.put(".mmf", "application/vnd.smaf");
    Mappings.put(".sdc", "application/vnd.stardivision.calc");
    Mappings.put(".sds", "application/vnd.stardivision.chart");
    Mappings.put(".sda", "application/vnd.stardivision.draw");
    Mappings.put(".sdd", "application/vnd.stardivision.impress");
    Mappings.put(".sdf", "application/vnd.stardivision.math");
    Mappings.put(".sdw", "application/vnd.stardivision.writer");
    Mappings.put(".sgl", "application/vnd.stardivision.writer-global");
    Mappings.put(".sxc", "application/vnd.sun.xml.calc");
    Mappings.put(".stc", "application/vnd.sun.xml.calc.template");
    Mappings.put(".sxd", "application/vnd.sun.xml.draw");
    Mappings.put(".std", "application/vnd.sun.xml.draw.template");
    Mappings.put(".sxi", "application/vnd.sun.xml.impress");
    Mappings.put(".sti", "application/vnd.sun.xml.impress.template");
    Mappings.put(".sxm", "application/vnd.sun.xml.math");
    Mappings.put(".sxw", "application/vnd.sun.xml.writer");
    Mappings.put(".sxg", "application/vnd.sun.xml.writer.global");
    Mappings.put(".stw", "application/vnd.sun.xml.writer.template");
    Mappings.put(".sis", "application/vnd.symbian.install");
    Mappings.put(".vsd", "application/vnd.visio");
    Mappings.put(".wbxml", "application/vnd.wap.wbxml");
    Mappings.put(".wmlc", "application/vnd.wap.wmlc");
    Mappings.put(".wmlsc", "application/vnd.wap.wmlscriptc");
    Mappings.put(".wpd", "application/vnd.wordperfect");
    Mappings.put(".wp5", "application/vnd.wordperfect5.1");
    Mappings.put(".wk", "application/x-123");
    Mappings.put(".7z", "application/x-7z-compressed");
    Mappings.put(".abw", "application/x-abiword");
    Mappings.put(".dmg", "application/x-apple-diskimage");
    Mappings.put(".bcpio", "application/x-bcpio");
    Mappings.put(".torrent", "application/x-bittorrent");
    Mappings.put(".cab", "application/x-cab");
    Mappings.put(".cbr", "application/x-cbr");
    Mappings.put(".cbz", "application/x-cbz");
    Mappings.put(".cdf", "application/x-cdf");
    Mappings.put(".cda", "application/x-cdf");
    Mappings.put(".vcd", "application/x-cdlink");
    Mappings.put(".pgn", "application/x-chess-pgn");
    Mappings.put(".cpio", "application/x-cpio");
    Mappings.put(".csh", "application/x-csh");
    Mappings.put(".deb", "application/x-debian-package");
    Mappings.put(".udeb", "application/x-debian-package");
    Mappings.put(".dcr", "application/x-director");
    Mappings.put(".dir", "application/x-director");
    Mappings.put(".dxr", "application/x-director");
    Mappings.put(".dms", "application/x-dms");
    Mappings.put(".wad", "application/x-doom");
    Mappings.put(".dvi", "application/x-dvi");
    Mappings.put(".rhtml", "application/x-httpd-eruby");
    Mappings.put(".pfa", "application/x-font");
    Mappings.put(".pfb", "application/x-font");
    Mappings.put(".gsf", "application/x-font");
    Mappings.put(".pcf", "application/x-font");
    Mappings.put(".pcf.Z", "application/x-font");
    Mappings.put(".mm", "application/x-freemind");
    Mappings.put(".gnumeric", "application/x-gnumeric");
    Mappings.put(".sgf", "application/x-go-sgf");
    Mappings.put(".gcf", "application/x-graphing-calculator");
    Mappings.put(".gtar", "application/x-gtar");
    Mappings.put(".tgz", "application/x-gtar");
    Mappings.put(".taz", "application/x-gtar");
    Mappings.put(".hdf", "application/x-hdf");
    Mappings.put(".phtml", "application/x-httpd-php");
    Mappings.put(".pht", "application/x-httpd-php");
    Mappings.put(".php", "application/x-httpd-php");
    Mappings.put(".phps", "application/x-httpd-php-source");
    Mappings.put(".php3", "application/x-httpd-php3");
    Mappings.put(".php3p", "application/x-httpd-php3-preprocessed");
    Mappings.put(".php4", "application/x-httpd-php4");
    Mappings.put(".php5", "application/x-httpd-php5");
    Mappings.put(".ica", "application/x-ica");
    Mappings.put(".info", "application/x-info");
    Mappings.put(".ins", "application/x-internet-signup");
    Mappings.put(".isp", "application/x-internet-signup");
    Mappings.put(".iii", "application/x-iphone");
    Mappings.put(".iso", "application/x-iso9660-image");
    Mappings.put(".jam", "application/x-jam");
    Mappings.put(".jnlp", "application/x-java-jnlp-file");
    Mappings.put(".jmz", "application/x-jmol");
    Mappings.put(".chrt", "application/x-kchart");
    Mappings.put(".kil", "application/x-killustrator");
    Mappings.put(".skp", "application/x-koan");
    Mappings.put(".skd", "application/x-koan");
    Mappings.put(".skt", "application/x-koan");
    Mappings.put(".skm", "application/x-koan");
    Mappings.put(".kpr", "application/x-kpresenter");
    Mappings.put(".kpt", "application/x-kpresenter");
    Mappings.put(".ksp", "application/x-kspread");
    Mappings.put(".kwd", "application/x-kword");
    Mappings.put(".kwt", "application/x-kword");
    Mappings.put(".latex", "application/x-latex");
    Mappings.put(".lha", "application/x-lha");
    Mappings.put(".lyx", "application/x-lyx");
    Mappings.put(".lzh", "application/x-lzh");
    Mappings.put(".lzx", "application/x-lzx");
    Mappings.put(".frm", "application/x-maker");
    Mappings.put(".maker", "application/x-maker");
    Mappings.put(".frame", "application/x-maker");
    Mappings.put(".fm", "application/x-maker");
    Mappings.put(".fb", "application/x-maker");
    Mappings.put(".book", "application/x-maker");
    Mappings.put(".fbdoc", "application/x-maker");
    Mappings.put(".mif", "application/x-mif");
    Mappings.put(".wmd", "application/x-ms-wmd");
    Mappings.put(".wmz", "application/x-ms-wmz");
    Mappings.put(".com", "application/x-msdos-program");
    Mappings.put(".exe", "application/x-msdos-program");
    Mappings.put(".bat", "application/x-msdos-program");
    Mappings.put(".dll", "application/x-msdos-program");
    Mappings.put(".msi", "application/x-msi");
    Mappings.put(".nc", "application/x-netcdf");
    Mappings.put(".pac", "application/x-ns-proxy-autoconfig");
    Mappings.put(".dat", "application/x-ns-proxy-autoconfig");
    Mappings.put(".nwc", "application/x-nwc");
    Mappings.put(".o", "application/x-object");
    Mappings.put(".oza", "application/x-oz-application");
    Mappings.put(".p7r", "application/x-pkcs7-certreqresp");
    Mappings.put(".crl", "application/x-pkcs7-crl");
    Mappings.put(".pyc", "application/x-python-code");
    Mappings.put(".pyo", "application/x-python-code");
    Mappings.put(".qgs", "application/x-qgis");
    Mappings.put(".shp", "application/x-qgis");
    Mappings.put(".shx", "application/x-qgis");
    Mappings.put(".qtl", "application/x-quicktimeplayer");
    Mappings.put(".rpm", "application/x-redhat-package-manager");
    Mappings.put(".rb", "application/x-ruby");
    Mappings.put(".sh", "application/x-sh");
    Mappings.put(".shar", "application/x-shar");
    Mappings.put(".swf", "application/x-shockwave-flash");
    Mappings.put(".swfl", "application/x-shockwave-flash");
    Mappings.put(".scr", "application/x-silverlight");
    Mappings.put(".sit", "application/x-stuffit");
    Mappings.put(".sitx", "application/x-stuffit");
    Mappings.put(".sv4cpio", "application/x-sv4cpio");
    Mappings.put(".sv4crc", "application/x-sv4crc");
    Mappings.put(".tar", "application/x-tar");
    Mappings.put(".tcl", "application/x-tcl");
    Mappings.put(".gf", "application/x-tex-gf");
    Mappings.put(".pk", "application/x-tex-pk");
    Mappings.put(".texinfo", "application/x-texinfo");
    Mappings.put(".texi", "application/x-texinfo");
    Mappings.put(".~", "application/x-trash");
    Mappings.put(".%", "application/x-trash");
    Mappings.put(".bak", "application/x-trash");
    Mappings.put(".old", "application/x-trash");
    Mappings.put(".sik", "application/x-trash");
    Mappings.put(".t", "application/x-troff");
    Mappings.put(".tr", "application/x-troff");
    Mappings.put(".roff", "application/x-troff");
    Mappings.put(".man", "application/x-troff-man");
    Mappings.put(".me", "application/x-troff-me");
    Mappings.put(".ms", "application/x-troff-ms");
    Mappings.put(".ustar", "application/x-ustar");
    Mappings.put(".src", "application/x-wais-source");
    Mappings.put(".wz", "application/x-wingz");
    Mappings.put(".crt", "application/x-x509-ca-cert");
    Mappings.put(".xcf", "application/x-xcf");
    Mappings.put(".fig", "application/x-xfig");
    Mappings.put(".xpi", "application/x-xpinstall");
    Mappings.put(".amr", "audio/amr");
    Mappings.put(".awb", "audio/amr-wb");
    Mappings.put(".axa", "audio/annodex");
    Mappings.put(".au", "audio/basic");
    Mappings.put(".snd", "audio/basic");
    Mappings.put(".flac", "audio/flac");
    Mappings.put(".mid", "audio/midi");
    Mappings.put(".midi", "audio/midi");
    Mappings.put(".kar", "audio/midi");
    Mappings.put(".mpga", "audio/mpeg");
    Mappings.put(".mpega", "audio/mpeg");
    Mappings.put(".mp2", "audio/mpeg");
    Mappings.put(".mp3", "audio/mpeg");
    Mappings.put(".m4a", "audio/mpeg");
    Mappings.put(".m3u", "audio/mpegurl");
    Mappings.put(".oga", "audio/ogg");
    Mappings.put(".ogg", "audio/ogg");
    Mappings.put(".spx", "audio/ogg");
    Mappings.put(".sid", "audio/prs.sid");
    Mappings.put(".aif", "audio/x-aiff");
    Mappings.put(".aiff", "audio/x-aiff");
    Mappings.put(".aifc", "audio/x-aiff");
    Mappings.put(".gsm", "audio/x-gsm");
    Mappings.put(".wma", "audio/x-ms-wma");
    Mappings.put(".wax", "audio/x-ms-wax");
    Mappings.put(".ra", "audio/x-pn-realaudio");
    Mappings.put(".rm", "audio/x-pn-realaudio");
    Mappings.put(".ram", "audio/x-pn-realaudio");
    Mappings.put(".pls", "audio/x-scpls");
    Mappings.put(".sd2", "audio/x-sd2");
    Mappings.put(".wav", "audio/x-wav");
    Mappings.put(".alc", "chemical/x-alchemy");
    Mappings.put(".cac", "chemical/x-cache");
    Mappings.put(".cache", "chemical/x-cache");
    Mappings.put(".csf", "chemical/x-cache-csf");
    Mappings.put(".cbin", "chemical/x-cactvs-binary");
    Mappings.put(".cascii", "chemical/x-cactvs-binary");
    Mappings.put(".ctab", "chemical/x-cactvs-binary");
    Mappings.put(".cdx", "chemical/x-cdx");
    Mappings.put(".cer", "chemical/x-cerius");
    Mappings.put(".c3d", "chemical/x-chem3d");
    Mappings.put(".chm", "chemical/x-chemdraw");
    Mappings.put(".cif", "chemical/x-cif");
    Mappings.put(".cmdf", "chemical/x-cmdf");
    Mappings.put(".cml", "chemical/x-cml");
    Mappings.put(".cpa", "chemical/x-compass");
    Mappings.put(".bsd", "chemical/x-crossfire");
    Mappings.put(".csml", "chemical/x-csml");
    Mappings.put(".csm", "chemical/x-csml");
    Mappings.put(".ctx", "chemical/x-ctx");
    Mappings.put(".cxf", "chemical/x-cxf");
    Mappings.put(".cef", "chemical/x-cxf");
    Mappings.put(".emb", "chemical/x-embl-dl-nucleotide");
    Mappings.put(".embl", "chemical/x-embl-dl-nucleotide");
    Mappings.put(".spc", "chemical/x-galactic-spc");
    Mappings.put(".inp", "chemical/x-gamess-input");
    Mappings.put(".gam", "chemical/x-gamess-input");
    Mappings.put(".gamin", "chemical/x-gamess-input");
    Mappings.put(".fch", "chemical/x-gaussian-checkpoint");
    Mappings.put(".fchk", "chemical/x-gaussian-checkpoint");
    Mappings.put(".cub", "chemical/x-gaussian-cube");
    Mappings.put(".gau", "chemical/x-gaussian-input");
    Mappings.put(".gjc", "chemical/x-gaussian-input");
    Mappings.put(".gjf", "chemical/x-gaussian-input");
    Mappings.put(".gal", "chemical/x-gaussian-log");
    Mappings.put(".gcg", "chemical/x-gcg8-sequence");
    Mappings.put(".gen", "chemical/x-genbank");
    Mappings.put(".hin", "chemical/x-hin");
    Mappings.put(".istr", "chemical/x-isostar");
    Mappings.put(".ist", "chemical/x-isostar");
    Mappings.put(".jdx", "chemical/x-jcamp-dx");
    Mappings.put(".dx", "chemical/x-jcamp-dx");
    Mappings.put(".kin", "chemical/x-kinemage");
    Mappings.put(".mcm", "chemical/x-macmolecule");
    Mappings.put(".mmd", "chemical/x-macromodel-input");
    Mappings.put(".mmod", "chemical/x-macromodel-input");
    Mappings.put(".mol", "chemical/x-mdl-molfile");
    Mappings.put(".rd", "chemical/x-mdl-rdfile");
    Mappings.put(".rxn", "chemical/x-mdl-rxnfile");
    Mappings.put(".sd", "chemical/x-mdl-sdfile");
    Mappings.put(".tgf", "chemical/x-mdl-tgf");
    Mappings.put(".mcif", "chemical/x-mmcif");
    Mappings.put(".mol2", "chemical/x-mol2");
    Mappings.put(".b", "chemical/x-molconn-Z");
    Mappings.put(".gpt", "chemical/x-mopac-graph");
    Mappings.put(".mop", "chemical/x-mopac-input");
    Mappings.put(".mopcrt", "chemical/x-mopac-input");
    Mappings.put(".mpc", "chemical/x-mopac-input");
    Mappings.put(".zmt", "chemical/x-mopac-input");
    Mappings.put(".moo", "chemical/x-mopac-out");
    Mappings.put(".mvb", "chemical/x-mopac-vib");
    Mappings.put(".asn", "chemical/x-ncbi-asn1");
    Mappings.put(".prt", "chemical/x-ncbi-asn1-ascii");
    Mappings.put(".ent", "chemical/x-ncbi-asn1-ascii");
    Mappings.put(".val", "chemical/x-ncbi-asn1-binary");
    Mappings.put(".aso", "chemical/x-ncbi-asn1-binary");
    Mappings.put(".pdb", "chemical/x-pdb");
    Mappings.put(".ros", "chemical/x-rosdal");
    Mappings.put(".sw", "chemical/x-swissprot");
    Mappings.put(".vms", "chemical/x-vamas-iso14976");
    Mappings.put(".vmd", "chemical/x-vmd");
    Mappings.put(".xtel", "chemical/x-xtel");
    Mappings.put(".xyz", "chemical/x-xyz");
    Mappings.put(".bmp", "image/bmp");
    Mappings.put(".gif", "image/gif");
    Mappings.put(".ief", "image/ief");
    Mappings.put(".jpeg", "image/jpeg");
    Mappings.put(".jpg", "image/jpeg");
    Mappings.put(".webp", "image/webp");
    Mappings.put(".jpe", "image/jpeg");
    Mappings.put(".pcx", "image/pcx");
    Mappings.put(".png", "image/png");
    Mappings.put(".svg", "image/svg+xml");
    Mappings.put(".svgz", "image/svg+xml");
    Mappings.put(".tiff", "image/tiff");
    Mappings.put(".tif", "image/tiff");
    Mappings.put(".djvu", "image/vnd.djvu");
    Mappings.put(".djv", "image/vnd.djvu");
    Mappings.put(".wbmp", "image/vnd.wap.wbmp");
    Mappings.put(".cr2", "image/x-canon-cr2");
    Mappings.put(".crw", "image/x-canon-crw");
    Mappings.put(".ras", "image/x-cmu-raster");
    Mappings.put(".cdr", "image/x-coreldraw");
    Mappings.put(".pat", "image/x-coreldrawpattern");
    Mappings.put(".cdt", "image/x-coreldrawtemplate");
    Mappings.put(".erf", "image/x-epson-erf");
    Mappings.put(".ico", "image/x-icon");
    Mappings.put(".art", "image/x-jg");
    Mappings.put(".jng", "image/x-jng");
    Mappings.put(".nef", "image/x-nikon-nef");
    Mappings.put(".orf", "image/x-olympus-orf");
    Mappings.put(".psd", "image/x-photoshop");
    Mappings.put(".pnm", "image/x-portable-anymap");
    Mappings.put(".pbm", "image/x-portable-bitmap");
    Mappings.put(".pgm", "image/x-portable-graymap");
    Mappings.put(".ppm", "image/x-portable-pixmap");
    Mappings.put(".rgb", "image/x-rgb");
    Mappings.put(".xbm", "image/x-xbitmap");
    Mappings.put(".xpm", "image/x-xpixmap");
    Mappings.put(".xwd", "image/x-xwindowdump");
    Mappings.put(".eml", "message/rfc822");
    Mappings.put(".igs", "model/iges");
    Mappings.put(".iges", "model/iges");
    Mappings.put(".msh", "model/mesh");
    Mappings.put(".mesh", "model/mesh");
    Mappings.put(".silo", "model/mesh");
    Mappings.put(".wrl", "model/vrml");
    Mappings.put(".vrml", "model/vrml");
    Mappings.put(".x3dv", "model/x3d+vrml");
    Mappings.put(".x3d", "model/x3d+xml");
    Mappings.put(".x3db", "model/x3d+binary");
    Mappings.put(".manifest", "text/cache-manifest");
    Mappings.put(".ics", "text/calendar");
    Mappings.put(".icz", "text/calendar");
    Mappings.put(".css", "text/css");
    Mappings.put(".csv", "text/csv");
    Mappings.put(".323", "text/h323");
    Mappings.put(".html", "text/html");
    Mappings.put(".htm", "text/html");
    Mappings.put(".shtml", "text/html");
    Mappings.put(".uls", "text/iuls");
    Mappings.put(".mml", "text/mathml");
    Mappings.put(".asc", "text/plain");
    Mappings.put(".txt", "text/plain");
    Mappings.put(".text", "text/plain");
    Mappings.put(".pot", "text/plain");
    Mappings.put(".brf", "text/plain");
    Mappings.put(".rtx", "text/richtext");
    Mappings.put(".sct", "text/scriptlet");
    Mappings.put(".wsc", "text/scriptlet");
    Mappings.put(".tm", "text/texmacs");
    Mappings.put(".ts", "text/texmacs");
    Mappings.put(".tsv", "text/tab-separated-values");
    Mappings.put(".jad", "text/vnd.sun.j2me.app-descriptor");
    Mappings.put(".wml", "text/vnd.wap.wml");
    Mappings.put(".wmls", "text/vnd.wap.wmlscript");
    Mappings.put(".bib", "text/x-bibtex");
    Mappings.put(".boo", "text/x-boo");
    Mappings.put(".h++", "text/x-c++hdr");
    Mappings.put(".hpp", "text/x-c++hdr");
    Mappings.put(".hxx", "text/x-c++hdr");
    Mappings.put(".hh", "text/x-c++hdr");
    Mappings.put(".c++", "text/x-c++src");
    Mappings.put(".cpp", "text/x-c++src");
    Mappings.put(".cxx", "text/x-c++src");
    Mappings.put(".cc", "text/x-c++src");
    Mappings.put(".h", "text/x-chdr");
    Mappings.put(".htc", "text/x-component");
    Mappings.put(".c", "text/x-csrc");
    Mappings.put(".d", "text/x-dsrc");
    Mappings.put(".diff", "text/x-diff");
    Mappings.put(".patch", "text/x-diff");
    Mappings.put(".hs", "text/x-haskell");
    Mappings.put(".java", "text/x-java");
    Mappings.put(".lhs", "text/x-literate-haskell");
    Mappings.put(".moc", "text/x-moc");
    Mappings.put(".p", "text/x-pascal");
    Mappings.put(".pas", "text/x-pascal");
    Mappings.put(".gcd", "text/x-pcs-gcd");
    Mappings.put(".pl", "text/x-perl");
    Mappings.put(".pm", "text/x-perl");
    Mappings.put(".py", "text/x-python");
    Mappings.put(".scala", "text/x-scala");
    Mappings.put(".etx", "text/x-setext");
    Mappings.put(".tk", "text/x-tcl");
    Mappings.put(".tex", "text/x-tex");
    Mappings.put(".ltx", "text/x-tex");
    Mappings.put(".sty", "text/x-tex");
    Mappings.put(".cls", "text/x-tex");
    Mappings.put(".vcs", "text/x-vcalendar");
    Mappings.put(".vcf", "text/x-vcard");
    Mappings.put(".3gp", "video/3gpp");
    Mappings.put(".axv", "video/annodex");
    Mappings.put(".dl", "video/dl");
    Mappings.put(".dif", "video/dv");
    Mappings.put(".dv", "video/dv");
    Mappings.put(".fli", "video/fli");
    Mappings.put(".gl", "video/gl");
    Mappings.put(".mpeg", "video/mpeg");
    Mappings.put(".mpg", "video/mpeg");
    Mappings.put(".mpe", "video/mpeg");
    Mappings.put(".mp4", "video/mp4");
    Mappings.put(".qt", "video/quicktime");
    Mappings.put(".mov", "video/quicktime");
    Mappings.put(".ogv", "video/ogg");
    Mappings.put(".mxu", "video/vnd.mpegurl");
    Mappings.put(".flv", "video/x-flv");
    Mappings.put(".lsf", "video/x-la-asf");
    Mappings.put(".lsx", "video/x-la-asf");
    Mappings.put(".mng", "video/x-mng");
    Mappings.put(".asf", "video/x-ms-asf");
    Mappings.put(".asx", "video/x-ms-asf");
    Mappings.put(".wm", "video/x-ms-wm");
    Mappings.put(".wmv", "video/x-ms-wmv");
    Mappings.put(".wmx", "video/x-ms-wmx");
    Mappings.put(".wvx", "video/x-ms-wvx");
    Mappings.put(".avi", "video/x-msvideo");
    Mappings.put(".movie", "video/x-sgi-movie");
    Mappings.put(".mpv", "video/x-matroska");
    Mappings.put(".mkv", "video/x-matroska");
    Mappings.put(".ice", "x-conference/x-cooltalk");
    Mappings.put(".sisx", "x-epoc/x-sisx-app");
    Mappings.put(".vrm", "x-world/x-vrml");
  }

  public static DocumentFormat getDocumentFormat(String extension) {
    if (extension.indexOf("\\.") == -1) {
      extension = "." + extension;
    }
    switch (extension.toLowerCase()) {
      case ".doc":
      case "doc":
      case "docx":
      case ".docx":
        return DocumentFormat.Word;
      case ".xls":
      case ".xlsx":
      case "xls":
      case "xlsx":
        return DocumentFormat.Excel;
      case ".ppt":
      case ".pptx":
      case "ppt":
      case "pptx":
        return DocumentFormat.Ppt;
      case ".pdf":
      case "pdf":
        return DocumentFormat.Pdf;
      case ".txt":
      case "txt":
      case "csv":
      case ".csv":
        return DocumentFormat.Text;
    }
    String mime = Mappings.get(extension.toLowerCase());
    if (mime == null) {
      return DocumentFormat.Unknown;
    }
    int index = mime.indexOf('/');
    if (index < 0) {
      return DocumentFormat.Unknown;
    }
    String prefix = mime.substring(0, index);
    switch (prefix) {
      case "audio":
        return DocumentFormat.Audio;
      case "image":
        if (mime.equalsIgnoreCase("image/bmp") || mime.equalsIgnoreCase("image/gif") ||
          mime.equalsIgnoreCase("image/jpeg") || mime.equalsIgnoreCase("image/png") ||
          mime.equalsIgnoreCase("image/webp") || mime.equalsIgnoreCase("image/svg+xml")) {
          return DocumentFormat.Image;
        }
        break;
      case "text":
        return DocumentFormat.Text;
      case "video":
        return DocumentFormat.Video;
    }
    return DocumentFormat.Unknown;
  }

  public static String getBasePath(String path) {
    Preconditions.checkNotNull(path);
    int index = path.indexOf(".");
    if (path.contains("thumb")) {
      index = path.lastIndexOf(".");
    }
    if (index > 0) {
      return path.substring(0, index);
    } else {
      return path;
    }
  }

  public static String getFileExt(String path) {
    Preconditions.checkNotNull(path);
    int index = path.lastIndexOf(".");
    if (index > 0) {
      return path.substring(index + 1);
    } else {
      return "";
    }
  }

  public static String getMime(String extension) {
    if (extension.indexOf("\\.") == -1) {
      extension = "." + extension;
    }
    String mime = Mappings.get(extension.toLowerCase());
    return mime;
  }

  public static String getMD5Fast(byte[] data) {
    //文件按2*1024*1024 （2M）分片，按顺序取每片的前1024字节（最后一片不足1024则取全部），合并起来作为md5的输入，取md5值
    int chunkSize = 1024 * 1024 * 2;
    int lastSize = data.length % chunkSize;
    byte[] bytes = new byte[(data.length / chunkSize) * 1024 + Math.min(1024, lastSize)];
    int offset = 0, count = 0;
    do {
      int len = Math.min(1024, data.length - offset);
      System.arraycopy(data, chunkSize * count, bytes, 1024 * count, len);
      count++;
      offset += chunkSize;
    } while (offset < data.length);
    return getMD5(bytes);
  }

  public static String getThumbBaseName(String path) {
    return path.substring(0, path.indexOf("."));
  }

  public static int getThumbIndex(String path) {
    return Integer.valueOf(path.split("\\.")[2]);
  }


  public static String getSha256(byte[] data) {
    return Base64Utils.encodeToString(DigestUtils.sha256(data));
  }

  public static String getMD5(byte[] data) {
    return DigestUtils.md5Hex(data);
  }

  public static String getSHA1(byte[] data) {
    return Base64Utils.encodeToString(DigestUtils.sha(data));
  }

}