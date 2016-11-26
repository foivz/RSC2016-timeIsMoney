<?php
namespace App\Http\Response;

class APIResponse
{

    protected $success = true;
    protected $responseCode = 200;
    protected $data = [];
    protected $messages = [];
    protected $errorInfo = [];

    /**
     * APIResponse constructor.
     * @param int $responseCode
     * @param array $data
     * @param array $messages
     * @param array $errorInfo
     */
    public function __construct($responseCode = 200, $data = [], $messages = [], $errorInfo = [])
    {
        $this->setStatusCode($responseCode);
        $this->data = $data;
        $this->messages = $messages;
        $this->errorInfo = $errorInfo;
    }

    /**
     * Returns the body payload that should be returned in json format.
     * @return string
     */
    public function getPayload()
    {
        $response = array(
            'success' => $this->success,
            'data' => $this->data,
            'messages' => $this->messages,
            'errorInfo' => $this->errorInfo,
        );
        return json_encode($response);
    }

    /**
     * @return string
     */
    public function __toString()
    {
        return $this->getPayload();
    }

    /**
     * Sets the HTTP status code the request will return.
     * @param $code
     * @return $this
     */
    public function setStatusCode($code)
    {
        if ($code >= 200 && $code < 300) {
            $this->success = true;
        } else {
            $this->success = false;
        }
        $this->responseCode = $code;
        return $this;
    }

    /**
     * Builds and returns a response
     * @return mixed|object
     */
    public function getResponse()
    {
        $factory = app('Illuminate\Contracts\Routing\ResponseFactory');
        return $factory->make($this->getPayload(), $this->responseCode, ['content-type' => 'application/json']);
    }

    /**
     * Response for errors
     *
     * @param string $message
     * @param string $errorCode
     * @param array $headers
     * @return mixed
     */
    public function withError($message, $errorCode, array $headers = [])
    {
        return $this->setStatusCode($this->responseCode)->addError([
                'code' => $errorCode,
                'http_code' => $this->responseCode,
                'message' => $message
            ])->getResponse();
    }

    /**
     * Adds error to the end of the error array.
     * @param $message
     * @return $this
     */
    public function addError($message)
    {
        $this->errorInfo[] = $message;
        return $this;
    }

    /**
     * Generates a response with a 403 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorForbidden($message = 'Forbidden')
    {
        return $this->setStatusCode(403)->addError($message)->getResponse();
    }

    /**
     * Generates a response with a 500 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorInternalError($message = 'Internal Error')
    {
        return $this->setStatusCode(500)->addError($message)->getResponse();
    }

    /**
     * Generates a response with a 404 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorNotFound($message = 'Resource Not Found')
    {
        return $this->setStatusCode(404)->addError($message)->getResponse();
    }

    /**
     * Generates a response with a 401 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorUnauthorized($message = 'Unauthorized')
    {
        return $this->setStatusCode(401)->addError($message)->getResponse();
    }

    /**
     * Generates a response with a 400 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorWrongArgs($message = 'Wrong Arguments')
    {
        return $this->setStatusCode(400)->addError($message)->getResponse();
    }

    /**
     * Generates a response with a 410 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorGone($message = 'Resource No Longer Available')
    {
        return $this->setStatusCode(410)->addError($message)->getResponse();
    }

    /**
     * Generates a response with a 405 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorMethodNotAllowed($message = 'Method Not Allowed')
    {
        return $this->setStatusCode(405)->addError($message)->getResponse();
    }

    /**
     * Generates a Response with a 431 HTTP header and a given message.
     *
     * @param string $message
     * @return mixed
     */
    public function errorUnwillingToProcess($message = 'Server is unwilling to process the request')
    {
        return $this->setStatusCode(431)->addError($message)->getResponse();
    }
}