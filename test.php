<?php

/**
 * http helper
 * Class HttpHelper
 * @package Common\Helper
 */
class HttpHelper
{
    /**
     * 发送HTTP请求方法，目前只支持CURL发送请求
     * @param  string $url 请求URL
     * @param  array $params 请求参数
     * @param  string $method 请求方法GET/POST
     * @param array $header
     * @param bool $multi
     * @return bool|string $data   响应数据
     * @throws Exception
     */
    public static function http($url, $params, $method = 'GET', $header = array(), $multi = false)
    {
        $opts = array(
            CURLOPT_TIMEOUT => 10,
            CURLOPT_RETURNTRANSFER => 1,
            CURLOPT_SSL_VERIFYPEER => false,
            CURLOPT_SSL_VERIFYHOST => false,
            CURLOPT_HTTPHEADER => $header
        );
        /* 根据请求类型设置特定参数 */
        switch (strtoupper($method)) {
            case 'GET':
                $opts[CURLOPT_URL] = $url . '?' . http_build_query($params);
                break;
            case 'POST':
                //判断是否传输文件
                $params = $multi ? $params : http_build_query($params);
                $opts[CURLOPT_URL] = $url;
                $opts[CURLOPT_POST] = 1;
                $opts[CURLOPT_POSTFIELDS] = $params;
                break;
            default:
                throw new Exception('不支持的请求方式！');
        }
        /* 初始化并执行curl请求 */
        $ch = curl_init();
        curl_setopt_array($ch, $opts);
        $data = curl_exec($ch);
        $error = curl_error($ch);
        curl_close($ch);
        if ($error)
            throw new Exception('请求发生错误：' . $error);
        return $data;
    }
}


$in = [
    'update_type' => '0',
    'bs_price' => '3.6',
    'amount' => 100,
    'bos' => 1,
    'trade_code' => 'sh601288',
];

//'token' => '10e58b298a59fc6d23c0ce612aa1a773',
//  'trade_code' => 'sh601288',
//  'title' => '农业银行',
//  'update_type' => '1',
//  'bs_price' => '3.69',
//  'amount' => '100',
//  'bos' => '1',
//  'password' => '',

$hp_trade = preg_replace("/(\d+)/i", "-$1", $in['trade_code']);
$trades = explode('-', $hp_trade);
$GD_CODES = [
    "sh" => "A422089377",
    "sz" => "0259345751",
];
$hp_gddm = $GD_CODES[$trades[0]];
$API_HOST = 'http://localhost:4050/';

$res = HttpHelper::http($API_HOST . "order", [
    'Category' => $in['bos'] == 1 ? 0 : 1,//和上面对应
    'PriceType' => $in['update_type'],
    'Gddm' => $hp_gddm,//计算股东代码
    'Zqdm' => $trades[1],
    'Price' => $in['bs_price'],
    'Quantity' => $in['amount'],
    'Token'=>'demo1'
], "POST");
var_dump("下单：", $res);

//取消订单
//计算股东代码
//交易所ID
/**
 * hth 表示要撤的目标委托的编号
 */
$res = HttpHelper::http($API_HOST . "cancel", ['Gddm' => "", 'Hth' => "",'Token'=>'demo1'], "POST");
var_dump("取消：", $res);