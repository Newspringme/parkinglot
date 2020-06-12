package com.cnzk.aoplog;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(getOptions());
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradePrecreateResponse response = Payment.FaceToFace()
                    .preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
            // 3. 处理响应或异常
            if (ResponseChecker.success(response)) {
                System.out.println("调用成功");
            } else {
                System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
            }
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "https://openapi.alipaydev.com/gateway.do";
        config.signType = "RSA2";

        config.appId = "2016102800774945";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCrf7ME3iZ42rx0r89HkwMKSpaWuLLOvI73HkhOdUVd4uKy3WkjQ9b4d6Xl5uwj8oindBtDkW00qgUdPXzuKD/T4ko9AijFjO8aq7yYgF+6yxR4SQwvkQnrhSiuM0Xf9sArIPomVY2d7owKuTIHvakyhxIkc/AFxYdSlqBsHomTznp/Ff1JmjhVXNa+v8d2BiWJTQfNozumOsFpFGqCaRktM2l1FsjISNZsKjqv4FU0zmOnpdNUu4KZaBIFH5XUyZb6fIVY6g8w+ooDcF+ymyWGpyOo0sJvs7tiECorAeY4qMSv60bXNFUX+0ALwaD44R2DqWFTAZgVZi0m34OkHq+1AgMBAAECggEAJUuD9s8Itsd6rDV/ciU6SAaocOaBnAvNW1guiUFfcGdivVsA/jZGjYoXfcOWHssjhKbvNuZtRUKkEhPJE1GVb9KKKnJJw4w+gyJNxSxFpS18qhvYlo+IBLvUeO2ViHr0l2x4edWpEmtJ8RsNEjngjahp/Zto2xh1sTQodtq9OHMWwS1BU6F16Zivj25mJiX9iqBlDgAH76Hf2gmtCjxU71mlnH784iaXw4pPtIAaJEpH5MR0ymC/qwpG60NTtatvkYyZSuvl6ZHeIt1Rx7jP3SMOAU7Re73Mw3MEeaQSLzWmR4XFtHKX5+bIV5QrsSPOkxlImt3pLIrK22RNmc8GkQKBgQDtVD58nOSzDKNf2T5/YMgtwvO2M9h6j9ZARzeGSERhw7Rod7YBCLiH7yr5p8TYyNQKWQ4UGQspP6ue4MQ4MsPi5f3m/E330VetNwuRy3z3juci/jcTYpnDa5a9/j8vzd+1xkW0GRkIzI1KaMoP69+svZTymop7W/xZMqMmLXmtnwKBgQC4/aX0DJQC0nw6U0GFyrtHceQQ5KPG6dA3WCdYHz8+bFLe92ddmyU3YuDgDlybKpZUuck5LszWKDJkPdK58RJ4bV6YMPnEpfsZWrzO9jtJPFBdIVxVjXAJCmFtGNk0YeEHfbpQ0RHOuohS7UATbylAFFOX9aLLxxC+yoGcONe6KwKBgBWKG9G7e5Y0g29jjCSLaB+fHfdfZTpaa5uR2xw42tv0ox8IHmChpf3QWxole9wg/f2ib4CrdTPdL3pP8/8VvGmPbifUpk7jlQ2HfWlSCpl9QsUORXlg7Yuq7Bd6R8nS6YbZ4+GglYES74/dStl/EuHeovIRzJe9lX2S9vO0FOMJAoGAeSoBgViftT2CCn2vg98mTQRGud3Xe5h3PViq5GgK92rh2JI5DLFqbd2ApqAhfawmLLZHGwIRPg+z+b4YgJtqWqBf/dTOM0P3tEKfOftDF5O+m1EXdh2ejP1UlK1azplxV4O8/eQCAuJgGdi1SEVdwk/N9ND5YPkweZaPz+/XNGMCgYB4WFQn5Ly1ZoN/M0cFUDtQJEYkCx83PJPI/+RmmXiHmck1CbsvdWAAzTalnncs2PMOmv6YcanutJWIWWzISNmN+uXk9Ici6IBwu5uNWqjvry+T7iWwyrzB4ptOauz/HrtEBdntlz4+wzHwH+iiOTh09ztaSYgRoCY6xA8jzQw9rg==";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
//        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
//        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
         config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjtfS2TX8VFash0JpSZU5/7K2D4YCK+eG54ND5FkGUEMaTjbwQgU0C4lzy/6XWmJH2k+5IEpZechic1vX+IcHpkXO+5AqS18f18oyExS//CaPmEjK0J8vEKg9kauYHawiMlJx8LsLG8QAueaCHBAqKRAOELHJjG3WabQVgd902mtATBH1MphbFI/lsR4V43PeELcL8aHZAYJzhSFF+BUZ/UlLcQj8dgN5VlEcjffMouO2XyuntcrBixOpPzBzF3GwIAMN3jaJQp/hZHhlRvBVCw/0LiGj8GB68MGKbuA976bX+NkWIfXN/Gb7WFKbJVVoN6X5rIPgl2kAqIhJF9pjTQIDAQAB";

        //可设置异步通知接收服务地址（可选）
//        config.notifyUrl = "<-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }
}