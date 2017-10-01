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

(defn swap-bytes [data]
  (->> data
       (partition-all 2)
       (map (juxt second first))
       (apply concat)
       (map #(or % 0))))
(defn make-buffer [data]
  (->> data
       (byte-array 130)))

(defn make-command [command key string]
  (-> (list command key (count string))
      (concat (.getBytes string))
      make-buffer
      bytes))

(defn send-init-command [hid]
  (.setFeatureReport hid 0 (byte-array 130 [0xc0]) 130))

(defn send-key-command [hid command key string]
  (let [buffer (make-command command key string)]
    (.setFeatureReport hid 1 buffer (count buffer))))

(defn define-key [hid key string]
  (send-key-command hid 0x10 key string)
  (send-key-command hid 0x20 key ""))
