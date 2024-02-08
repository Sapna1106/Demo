package com.example.demo.Service.impl;

import com.example.demo.Service.IUserService;
import com.example.demo.entity.User;
import com.example.demo.repository.IUserRepository;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Log4j2
@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    @Override
    public ByteArrayOutputStream createSelfDeclaration() throws DocumentException, IOException {
        PdfReader pdfReader = new PdfReader(new ClassPathResource("SelfDeclaration.pdf").getInputStream());
        ByteArrayOutputStream outputFile = new ByteArrayOutputStream();
        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputFile);
        pdfStamper.setFullCompression();
        AcroFields form = pdfStamper.getAcroFields();

        log.info(form.getFields());
        form.setField("FormID", "RUPY014537");
        setFieldFontSize(form, "FormID", 10);
        String paragraph = "Splited User Entity to user and UserCompanyDetails and removed TeamMember Entity\n" +
                " refactored changes to the service and controller.Splited User Entity to user and UserCompanyDetails and removed TeamMember Entity\n" +
                " refactored changes to the service and controller";

        wrapTextInParagraph(pdfStamper, "Address", paragraph);
        form.setField("Name", "VINAYAGAM");
        setFieldFontSize(form, "Name", 7);
        pdfStamper.setFormFlattening(true);

        pdfStamper.close();
        pdfReader.close();
        return outputFile;
    }

    private static void wrapTextInParagraph(PdfStamper form, String fieldName, String paragraph) throws IOException, DocumentException {
        PdfContentByte canvas = form.getOverContent(1);
        Rectangle rect = form.getAcroFields().getFieldPositions(fieldName).get(0).position;
        Font font = new Font();
        font.setSize(7);

        Phrase phrase = new Phrase(paragraph, font);

        ColumnText ct = new ColumnText(canvas);
        ct.setSimpleColumn(rect);
        ct.addElement(phrase);
        ct.go();
    }

    private static void setFieldFontSize(AcroFields form, String fieldName, float fontSize) {
        form.setFieldProperty(fieldName, "textsize", fontSize, null);
    }

    @Override
//    @Async
    @Retryable(value = {Exception.class}, maxAttemptsExpression = "${retry.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.delay:1000}"))
    public ByteArrayOutputStream createPdf() throws Exception {
//        PdfReader pdfReader = new PdfReader(new ClassPathResource("EKYCAuthImage.pdf").getInputStream());
//        ByteArrayOutputStream outputFile = new ByteArrayOutputStream();
//        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputFile);
//        PdfContentByte pdfContentByte = pdfStamper.getOverContent(1);
//        PdfTemplate template = pdfContentByte.createTemplate(100, 100);
//        Image userPicture = Image.getInstance(ClassLoader.getSystemResource("download.jpg"));
//        userPicture.scaleToFit(100, 100);
//        userPicture.setAbsolutePosition(0, 0);
//        template.addImage(userPicture);
//        template.moveTo(0, 0);
//        pdfContentByte.addTemplate(template, 1, 0, 0, 1, 0, 0);
//        pdfContentByte.setColorFill(BaseColor.WHITE);
//        pdfContentByte.fillStroke();
//        pdfContentByte.clip();
//        pdfContentByte.newPath();
//        pdfContentByte.addImage(userPicture);

        PdfReader pdfReader = new PdfReader(new ClassPathResource("EKYCAuthImage 4.pdf").getInputStream());
        ByteArrayOutputStream outputFile = new ByteArrayOutputStream();
        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputFile);
        pdfStamper.setFullCompression();
        AcroFields form = pdfStamper.getAcroFields();

//        String imageFieldName = "Image_af_image";
        String imageUrl = String.valueOf(new ClassPathResource("customer_photo.PNG").getURL());
        Image image = Image.getInstance(imageUrl);
//        PdfDictionary fieldDict = form.getFieldItem(imageFieldName).getMerged(0);
//        PdfArray rectArray = fieldDict.getAsArray(PdfName.RECT);
//        float xPosition = rectArray.getAsNumber(0).floatValue();
//        float yPosition = rectArray.getAsNumber(1).floatValue();
//        float width = rectArray.getAsNumber(2).floatValue() - xPosition;
//        float height = rectArray.getAsNumber(3).floatValue() - yPosition;
//        System.out.println("xPosition: " + xPosition);
//        System.out.println("yPosition: " + yPosition);
//        System.out.println("width: " + width);
//        System.out.println("height: " + height);
//        xPosition: 42.6624
//        yPosition: 615.182
//        width: 143.9996
//        height: 144.0
        image.setAbsolutePosition(70F, 665F);
        image.scaleAbsolute(90F, 90F);
        PdfContentByte contentByte = pdfStamper.getOverContent(1);

//        PdfTemplate template = contentByte.createTemplate(width, height);
        contentByte.addImage(image);
//        contentByte.addTemplate(template, xPosition, yPosition+10);
//        contentByte.addImage(image, false);

//        log.info(form.getFields());
        form.setField("FormID", "RUPY014537");
        form.setField("AadhaarNo", "XXXX XXXX 0504");
        form.setField("FirstName", "VINAYAGAM");
        form.setField("ContactPerson", "Rahul");
        form.setField("FatherName", "Rohan");
        form.setField("DOB", "1978-02-20");
        form.setField("Gender", "M");
        form.setField("Email", "vinayagam@gmail.com");
        form.setField("MobileNo", "8147765101");
        form.setField("Address", "NO150 BINNYPET");
        form.setField("HouseNo", "123");
        form.setField("Landmark", "xyz");
        form.setField("City", "BANGALORE");
        form.setField("Street", "KARNATAKA");
        form.setField("PostOffice", "BANGALORE");
        form.setField("District", "BANGALORE");
        form.setField("SubDistrict", "BANGALORE");
        form.setField("State", "KARNATAKA");
        form.setField("Pincode", "560023");
        form.setField("Country", "India");
        String field = form.getField("Country");
//        log.info("Country: ", field.length());
        pdfStamper.setFormFlattening(true);

        pdfStamper.close();
        pdfReader.close();
        return outputFile;

    }

    public ByteArrayOutputStream setImage() throws Exception {
        PdfReader pdfReader = new PdfReader(new ClassPathResource("EKYCAuthImage.pdf").getInputStream());
        ByteArrayOutputStream outputFile = new ByteArrayOutputStream();
        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputFile);
        pdfStamper.setFullCompression();
        AcroFields form = pdfStamper.getAcroFields();

        File file = new File("EKYCAuthImage.pdf");
        PDDocument doc = PDDocument.load(file);
        PDPage page = doc.getPage(1);

        PDImageXObject pdImage = PDImageXObject.createFromFile("customer_photo.PNG", doc);
        String imageFieldName = "Image_af_image";
        String imageUrl = String.valueOf(new ClassPathResource("customer_photo.PNG").getURL());
        Image image = Image.getInstance(imageUrl);
        PdfDictionary fieldDict = form.getFieldItem(imageFieldName).getMerged(0);
        PdfArray rectArray = fieldDict.getAsArray(PdfName.RECT);
        float xPosition = rectArray.getAsNumber(0).floatValue();
        float yPosition = rectArray.getAsNumber(1).floatValue();
        float width = rectArray.getAsNumber(2).floatValue() - xPosition;
        float height = rectArray.getAsNumber(3).floatValue() - yPosition;

        image.setAbsolutePosition(xPosition, yPosition + 50);
        image.scaleAbsolute(width, height);

        PDPageContentStream contents = new PDPageContentStream(doc, page);
        contents.drawImage(pdImage, xPosition, 300);
        contents.close();
        return null;
    }

    @Override
    @Retryable(value = NullPointerException.class, maxAttempts = 4, backoff = @Backoff(delay = 2000))
    public User saveUser(User user) {
        try {
//            log.info("Retry Number : {}", RetrySynchronizationManager.getContext().getRetryCount());
            return userRepository.save(user);
        } catch (Exception e) {
            throw e;
        }
    }

}
