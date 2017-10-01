(ns ek20-program.core
  (:import [purejavahidapi PureJavaHidApi]))

(defn find-keyboard []
  (->> (PureJavaHidApi/enumerateDevices)
       (filter #(= (.getProductString %) "KB200"))
       first))

(defn open-keyboard []
  (if-let [dev-info (find-keyboard)]
    (PureJavaHidApi/openDevice dev-info)
    (throw (ex-info "Could not find KB200 keyboard attached to USB bus" {}))))

(defn make-buffer [data]
  (byte-array 130 data))

(defn make-command [command key string]
  (-> (list command key (count string))
      (concat (.getBytes string))
      make-buffer
      bytes))

(defn send-key-command [hid command key string]
  (let [buffer (make-command command key string)]
    (.setFeatureReport hid 1 buffer (count buffer))))

(defn define-key [hid key string]
  (send-key-command hid 0x10 key string)
  (send-key-command hid 0x20 key ""))
