package com.ukuloskit.mylang.frontend.pascal.tokens;

import com.ukuloskit.mylang.frontend.Source;
import com.ukuloskit.mylang.frontend.pascal.PascalErrorCode;
import com.ukuloskit.mylang.frontend.pascal.PascalTokenType.*;

import java.io.IOException;

import static com.ukuloskit.mylang.frontend.pascal.PascalTokenType.ERROR;

/**
 * Created by uku on 14.11.16.
 */
public class PascalErrorToken extends PascalToken {

    public PascalErrorToken(Source source, PascalErrorCode errorCode,
                            String tokenText) throws IOException {
        super(source);
        this.text = tokenText;
        this.type = ERROR;
        this.value = errorCode;

    }

    protected void extract() {

    }

}
