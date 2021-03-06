/*
XELOPES Java Version 3.2
Copyright (C) 2008  prudsys AG

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
version 2 as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/

/**
  * Title: XELOPES Data Mining Library
  * Description: The XELOPES library is an open platform-independent and data-source-independent library for Embedded Data Mining.
  * Copyright: Copyright (c) 2002-2005 prudsys AG. All Rights Reserved.
  * License: Use is subject to XELOPES license terms.
  * @author Valentine Stepanenko (valentine.stepanenko@zsoft.ru)
  * @version 1.0
  */

package org.eltech.ddm.inputdata.file;

import java.io.*;
import java.util.*;

import org.eltech.ddm.inputdata.MiningInputStream;
import org.eltech.ddm.inputdata.MiningVector;
import org.eltech.ddm.miningcore.MiningDataException;
import org.eltech.ddm.miningcore.MiningErrorCode;
import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningdata.EAttributeAssignmentSet;
import org.eltech.ddm.miningcore.miningdata.ELogicalData;
import org.eltech.ddm.miningcore.miningdata.EPhysicalData;
import org.omg.java.cwm.analysis.transformation.TransformationMap;

/**
 * Mining input stream class for files.
 *
 * For special file formats this class should be
 * extended.
 */
public abstract class MiningFileStream extends MiningInputStream
{
    // -----------------------------------------------------------------------
    //  Variables definitions
    // -----------------------------------------------------------------------
    /** File name. */
    protected String fileName;

    /** File reader object. */
    protected Reader reader;

    // -----------------------------------------------------------------------
    //  Constructor
    // -----------------------------------------------------------------------
    /**
     * Empty constructor.
     */
    public MiningFileStream()
    {
    	super();
    }

    /**
     * Mining file stream for a given file and given meta data.
     *
     * @param file path of file to access
     * @param metaData meta data of file data
     * @throws MiningException invalid file path
     */
    public MiningFileStream( String file, ELogicalData logicalData ) throws MiningException
    {
        this();
    	this.fileName = file;
        this.logicalData = logicalData;
        this.reader = null;
    }

    /**
     * Mining file stream for a given file.
     * The meta data is automatically determined using the
     * method createMetaData.
     *
     * @param file path of file to access
     * @throws MiningException invalid file path
     */
    public MiningFileStream( String file ) throws MiningException
    {
        this( file, null );
    }

    /**
     * Finds physical file model (CWM Resource Package "Record").
     *
     * @exception MiningException couldn't obtain physical model
     */
//     public void findPhysicalModel() throws MiningException {
//
//       com.prudsys.pdm.Cwm.CWMCompletePackage cwmFactory =
//           com.prudsys.pdm.Cwm.CWMCompletePackage.getCWMCompletePackage();
//       CorePackage cpg   = cwmFactory.getCore();
//       RecordPackage rpg = cwmFactory.getRecord();
//       RecordFile rfile  = rpg.getRecordFile().createRecordFile();
//       rfile.setName(fileName);
//       RecordDef recdef  = rpg.getRecordDef().createRecordDef();
//       rfile.addRecord(recdef);
//       for (int i = 0; i < metaData.getAttributesNumber(); i++) {
//         Field field = rpg.getField().createField();
//         MiningAttribute ma = metaData.getMiningAttribute(i);
//         field.setName( ma.getName() );
//         DataType dataType = cpg.getDataType().createDataType();
//         String dtname = "unknownType";
//         if (ma instanceof NumericAttribute) {
//           if (ma.getDataType() == NumericAttribute.DOUBLE) dtname = "double";
//           else if (ma.getDataType() == NumericAttribute.FLOAT) dtname = "float";
//           else if (ma.getDataType() == NumericAttribute.INTEGER) dtname = "integer";
//           else if (ma.getDataType() == NumericAttribute.BOOLEAN) dtname = "boolean";
//           else if (ma.getDataType() == NumericAttribute.DATETIME_PRUDSYS) dtname = "datePrudsys";
//           else dtname = "numeric";
//         }
//         else {
//           if (ma.getDataType() == CategoricalAttribute.STRING) dtname = "string";
//           else if (ma.getDataType() == CategoricalAttribute.BOOLEAN) dtname = "boolean";
//           else dtname = "categorical";
//         };
//         dataType.setName(dtname);
//         field.setType(dataType);
//         recdef.addFeature(field);
//       }
//
//       physicalModel = rfile;
//     }

     /**
      * Returns the CWM mapping from the physical to the logical data model.
      *
      * @return transformation of physical to logical data model
      * @throws MiningException couldn't getElement transformation
      */
//     public TransformationMap getPhysicalToLogicalModelTransformation()
//         throws MiningException {
//
//       com.prudsys.pdm.Cwm.CWMCompletePackage cwmFactory =
//           com.prudsys.pdm.Cwm.CWMCompletePackage.getCWMCompletePackage();
//       TransformationPackage tpg = cwmFactory.getTransformation();
//
//       TransformationMap tm = tpg.getTransformationMap().createTransformationMap();
//       ClassifierMap cm = tpg.getClassifierMap().createClassifierMap();
//       tm.addOwnedElement(cm);
//
//       RecordFile rfile = (RecordFile) getPhysicalModel();
//       Iterator it      = rfile.getRecord().iterator();
//       RecordDef recdef = (RecordDef) it.next();
//       it               = recdef.getFeature().iterator();
//       for (int i = 0; i < metaData.getAttributesNumber(); i++) {
//         FeatureMap fm = tpg.getFeatureMap().createFeatureMap();
//         fm.addSource( (Field) it.next() );
//         fm.addTarget( metaData.getMiningAttribute(i) );
//         cm.addFeatureMap(fm);
//       };
//
//       metaData.addOwnedElement(tm);
//       physicalModel.addOwnedElement(tm);
//
//       return tm;
//     }

    /**
     * Returns file name.
     *
     * @return file name
     */
    public String getFileName()
    {
        return fileName;
    }

    // -----------------------------------------------------------------------
    //  General stream methods
    // -----------------------------------------------------------------------
    /**
     * Open mining file stream. This method can be left.
     *
     * @exception MiningException if a mining source access error occurs
     */
    public void open() throws MiningException
    {
      try
      {
          reader = new BufferedReader( new FileReader( this.fileName ) );
          this.open = true;
//          if(this.physicalData==null) {
//        	  physicalData = recognize();
//            reset();
//          }
      }
      catch( IOException ex)
      {
          this.reader = null;
          this.physicalData = null;
          this.open = false;
          throw new MiningDataException("Can't read from the file: "+this.fileName );
      }

      reset();
    }

    /**
     * Close mining file stream by closing reader.
     *
     * @exception MiningException if a mining source access error occurs
     */
    public void close() throws MiningException
    {
      if(!this.isOpen())
        throw new MiningDataException("Stream is already closed");

      this.open = false;
      try
      {
          reader.close();
      }
      catch( IOException ex)
      {
          throw new MiningDataException("Can't close reader from the file: "+fileName );
      }
      catch( NullPointerException ex) { // stream is still/already closed
      }
      reader = null;
    }

    /**
     * Recognize the input stream data specification. Not implemented.
     *
     * @return     the MiningDataSpecification
     * @exception  MiningException always thrown
     */
    public EPhysicalData recognize() throws MiningException
    {
        throw new MiningException( MiningErrorCode.UNSUPPORTED );
    }

    // -----------------------------------------------------------------------
    //  Methods of cursor positioning
    // -----------------------------------------------------------------------
    /**
     * Places the cursor before first row.
     * This is done by closing and reopening the file reader.
     *
     * @throws MiningException reset error
     */
    public void reset() throws MiningException
    {
        if(!this.isOpen())
          throw new MiningException(MiningErrorCode.INVALID_INPUT_DATA, "Can't reset closed stream. Call open()");

        try
        {
            if( reader!= null) reader.close();
            reader = new BufferedReader( new FileReader( this.fileName ) );
            this.open = true;
            cursorPosition = -1;
        }
        catch( IOException ex )
        {
//            this.open = false;
//            throw new MiningException( ex.getMessage() );
            this.reader = null;
            this.physicalData = null;
            this.open = false;
            throw new MiningDataException( "Can't read from the file: "+this.fileName );
        }
    }

    /**
     * Advance cursor by one position. Not implemented.
     *
     * @return true if next vector exists, else false
     * @throws MiningException always thrown
     */

	// public abstract boolean next() throws MiningException;
    public abstract MiningVector readPhysicalRecord() throws MiningException;



    /**
     * Move cursor to given position. Not supported.
     *
     * @param position new cursor position
     * @return true if possible, else false
     * @throws MiningException always thrown
     */
    //protected abstract MiningVector move( int position ) throws MiningException;
    protected abstract MiningVector movePhysicalRecord( int position ) throws MiningException;
    

    // -----------------------------------------------------------------------
    //  Methods of reading from the stream
    // -----------------------------------------------------------------------
    /**
     * Reads current data vector. Not implemented.
     *
     * @return data vector at current cursor position
     * @exception MiningException always thrown
     */
   // protected abstract MiningVector readVector() throws MiningException;


    // -----------------------------------------------------------------------
    //  Other methods
    // -----------------------------------------------------------------------
    /**
     * Returns string representation of mining file stream
     * (just the file name).
     *
     * @return string representation of mining file stream
     */
    public String toString()
    {
        return fileName + "/n" + super.toString();
    }

    /**
     * Returns HTML string representation of mining file
     * stream.
     *
     * @return HTML string representation of mining file stream
     */
    public String toHtmlString()
    {
        String separator = System.getProperty( "file.separator" );
        String description = "<html>";
//        String path = "";
//        String token = "";
//        StringTokenizer st = new StringTokenizer( fileName, separator, false );
//        while( st.hasMoreTokens() )
//        {
//            token = st.nextToken();
//            path = path + token + separator;
//            description = description + "<a href=http://this?" + path + ">" +  token + separator + "</a>";
//        }
//        int d = description.lastIndexOf( separator +  "</a>" );
//        description = description.substring( 0, d ) + "</a>";
        description = description + "Data source:<br>" +
        "<a href=http:this?" + fileName + ">" +  fileName + "</a>";
        description = description + "</html>";

        return description;
    }
    
	public Object clone() {
		MiningFileStream o = null;
		
		o = (MiningFileStream)super.clone();

		o.fileName = fileName;

	    if(isOpen()){
			try {
				o.open();
			} catch (MiningException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		
	    return o;
	}


}
