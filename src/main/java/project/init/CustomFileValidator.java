package project.init;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import project.model.view.BookViewModel;

@Component
public abstract class CustomFileValidator implements Validator {
    public static final String PNG_TYPE = "image/png";
    public static final String JPEG_TYPE = "image/jpeg";
    public static final String GIF_TYPE = "image/gif";
    public static final String BMP_TYPE = "image/bmp";
    public static final long TEN_MB_IN_BYTES = 10485760;

    @Override
    public boolean supports(Class<?> clazz) {
        return BookViewModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookViewModel bookViewModel = (BookViewModel) target;
        MultipartFile image = bookViewModel.getImage();

        if (image.isEmpty()) {
            errors.rejectValue("image", "Upload file required!!");
        } else if (!PNG_TYPE.equalsIgnoreCase(image.getContentType())
                || !JPEG_TYPE.equalsIgnoreCase(image.getContentType())
                || !BMP_TYPE.equalsIgnoreCase(image.getContentType())
                || !GIF_TYPE.equalsIgnoreCase(image.getContentType())) {
            errors.rejectValue("image", "Allowed only files - .jpeg,.jpg,.gif,.bmp,.png!");
        } else if (image.getSize() > TEN_MB_IN_BYTES) {
            errors.rejectValue("image", "Uploaded file exceeded 10MB!");
        }
    }
}
